package com.melkopisi.data.remote.mappers

import com.melkopisi.data.remote.models.DailyForecastRemote
import com.melkopisi.domain.models.DailyForecastModel.Forecast
import com.melkopisi.domain.models.DailyForecastModel.Forecast.Weather

fun DailyForecastRemote.Forecast.mapToDailyForecastModel() =
  Forecast(weather = this.weather.map {
    it.mapToWeatherModel()
  })

fun DailyForecastRemote.Forecast.Weather.mapToWeatherModel() =
  Weather(id = id, main = main, description = description)