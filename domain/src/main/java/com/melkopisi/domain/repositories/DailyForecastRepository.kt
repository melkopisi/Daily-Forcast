package com.melkopisi.domain.repositories

import com.melkopisi.domain.models.DailyForecastModel
import io.reactivex.Single

interface DailyForecastRepository {
  fun getDailyForecast(cityName:String): Single<DailyForecastModel>
}