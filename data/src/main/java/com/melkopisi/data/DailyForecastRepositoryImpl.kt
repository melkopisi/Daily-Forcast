package com.melkopisi.data

import com.melkopisi.data.remote.DailyForecastApi
import com.melkopisi.data.remote.mappers.mapToDailyForecastModel
import com.melkopisi.domain.exceptions.NoDataException
import com.melkopisi.domain.models.DailyForecastModel
import com.melkopisi.domain.repositories.DailyForecastRepository
import io.reactivex.Single
import javax.inject.Inject

class DailyForecastRepositoryImpl @Inject constructor(
  private val remote: DailyForecastApi
) : DailyForecastRepository {
  override fun getDailyForecast(cityName: String): Single<List<DailyForecastModel.Forecast>> {
    return remote.getDailyForecast(BuildConfig.API_KEY, cityName)
      .flatMap {
        if (it.cod != "200") {
          Single.error(NoDataException)
        } else {
          Single.just(it.list.map { forecast ->
            forecast.mapToDailyForecastModel()
          })
        }
      }
  }
}