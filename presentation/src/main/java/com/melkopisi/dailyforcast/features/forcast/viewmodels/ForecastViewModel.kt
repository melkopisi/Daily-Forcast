package com.melkopisi.dailyforcast.features.forcast.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.melkopisi.dailyforcast.R
import com.melkopisi.dailyforcast.features.forcast.mappers.mapToDailyForecastUiModel
import com.melkopisi.dailyforcast.features.forcast.models.DailyForecastUiModel
import com.melkopisi.dailyforcast.general.BaseViewModel
import com.melkopisi.dailyforcast.general.Resource
import com.melkopisi.dailyforcast.general.extensions.setError
import com.melkopisi.dailyforcast.general.extensions.setLoading
import com.melkopisi.dailyforcast.general.extensions.setSuccess
import com.melkopisi.domain.exceptions.NoDataException
import com.melkopisi.domain.usecases.GetDailyForecastUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
  private val dailyForecastUseCase: GetDailyForecastUseCase
) : BaseViewModel() {

  private val _getDailyForecastLiveData =
    MutableLiveData<Resource<List<DailyForecastUiModel.Forecast>>>()
  val getDailyForecastLiveData: LiveData<Resource<List<DailyForecastUiModel.Forecast>>> =
    _getDailyForecastLiveData

  fun getDailyForecast(cityName: String) {
    dailyForecastUseCase(cityName)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSubscribe { _getDailyForecastLiveData.setLoading() }
      .subscribe({
        _getDailyForecastLiveData.setSuccess(
          it.map { forecast ->
            forecast.mapToDailyForecastUiModel()
          }
        )
      }, { throwable ->
        _getDailyForecastLiveData.setError(mapError(throwable))
      }).addDisposable()
  }

  private fun mapError(throwable: Throwable): Int {
    return when (throwable) {
      is NoDataException -> R.string.no_data_error
      else -> R.string.general_error
    }
  }
}