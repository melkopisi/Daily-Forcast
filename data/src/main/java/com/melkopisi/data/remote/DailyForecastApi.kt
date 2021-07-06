package com.melkopisi.data.remote

import com.melkopisi.data.remote.models.DailyForecastRemote
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

private object Forecast{
  const val DAILY_FORECAST_API="/data/2.5/forecast/"
}
interface DailyForecastApi {
  @GET(Forecast.DAILY_FORECAST_API)
  fun getDailyForecast(
    @Query("q") cityName: String,
    @Query("appid") appId: String
  ): Single<DailyForecastRemote>


}