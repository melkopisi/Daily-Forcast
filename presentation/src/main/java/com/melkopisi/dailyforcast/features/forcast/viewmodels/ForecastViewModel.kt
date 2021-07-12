package com.melkopisi.dailyforcast.features.forcast.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.melkopisi.dailyforcast.R
import com.melkopisi.dailyforcast.di.application.qualifires.Background
import com.melkopisi.dailyforcast.di.application.qualifires.ForeGround
import com.melkopisi.dailyforcast.features.forcast.mappers.mapToDailyForecastUiModel
import com.melkopisi.dailyforcast.features.forcast.models.DailyForecastUiModel
import com.melkopisi.dailyforcast.general.BaseViewModel
import com.melkopisi.dailyforcast.general.Resource
import com.melkopisi.dailyforcast.general.extensions.setError
import com.melkopisi.dailyforcast.general.extensions.setLoading
import com.melkopisi.dailyforcast.general.extensions.setLocalSuccess
import com.melkopisi.dailyforcast.general.extensions.setSuccess
import com.melkopisi.domain.exceptions.NoDataException
import com.melkopisi.domain.exceptions.NoRemoteDataException
import com.melkopisi.domain.usecases.GetDailyForecastUseCase
import io.reactivex.Scheduler
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
  private val dailyForecastUseCase: GetDailyForecastUseCase,
  @Background private val backgroundScheduler: Scheduler,
  @ForeGround private val foregroundScheduler: Scheduler
) : BaseViewModel() {

  private val _getDailyForecastLiveData =
    MutableLiveData<Resource<DailyForecastUiModel>>()
  val getDailyForecastLiveData: LiveData<Resource<DailyForecastUiModel>> =
    _getDailyForecastLiveData

  fun getDailyForecast(cityName: String) {
    dailyForecastUseCase(cityName)
      .subscribeOn(backgroundScheduler)
      .observeOn(foregroundScheduler)
      .doOnSubscribe { _getDailyForecastLiveData.setLoading() }
      .subscribe({
        _getDailyForecastLiveData.setSuccess(
          it.mapToDailyForecastUiModel()
        )
      }, { throwable ->
        if (throwable is NoRemoteDataException) {
          if (throwable.data.list.isNullOrEmpty().not()) {
            _getDailyForecastLiveData.setLocalSuccess(throwable.data.mapToDailyForecastUiModel())
          } else {
            _getDailyForecastLiveData.setError(R.string.no_data_error)
          }
        } else {
          _getDailyForecastLiveData.setError(mapError(throwable))
        }
      }).addDisposable()
  }

  private fun mapError(throwable: Throwable): Int {
    return when (throwable) {
      is NoDataException -> R.string.no_data_error
      else -> R.string.general_error
    }
  }
}