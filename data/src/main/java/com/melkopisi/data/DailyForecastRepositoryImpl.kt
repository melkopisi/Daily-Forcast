package com.melkopisi.data

import com.melkopisi.data.local.ForecastDao
import com.melkopisi.data.local.mappers.mapToDailyForecastEntity
import com.melkopisi.data.local.mappers.mapToDailyForecastModel
import com.melkopisi.data.remote.DailyForecastApi
import com.melkopisi.data.remote.mappers.mapToDailyForecastModel
import com.melkopisi.domain.exceptions.NoDataException
import com.melkopisi.domain.exceptions.NoRemoteDataException
import com.melkopisi.domain.models.DailyForecastModel
import com.melkopisi.domain.repositories.DailyForecastRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class DailyForecastRepositoryImpl @Inject constructor(
  private val remote: DailyForecastApi,
  private val local: ForecastDao
) : DailyForecastRepository {
  override fun getDailyForecast(cityName: String): Single<DailyForecastModel> {
    return remote.getDailyForecast(BuildConfig.API_KEY, cityName)
      .flatMap {
        if (it.cod != "200") {
          Single.error(NoDataException)
        } else {
          Completable.fromCallable {
            local.saveForecast(it.mapToDailyForecastEntity())
          }.andThen(Single.just(it.mapToDailyForecastModel()))
        }
      }.onErrorResumeNext {
        local.getForecast().flatMap {
          val forecastList = it.mapToDailyForecastModel()
          Single.error(NoRemoteDataException(forecastList))
        }
      }
  }
}