package com.melkopisi.data.remote.mappers

import com.melkopisi.data.remote.models.DailyForecastRemote
import com.melkopisi.domain.models.DailyForecastModel

fun DailyForecastRemote.mapToDailyForecastModel() =
  DailyForecastModel(cod = cod, list = list.map { it.mapToInnerForecastModel() })

fun DailyForecastRemote.Forecast.mapToInnerForecastModel() =
  DailyForecastModel.Forecast(weather = this.weather.map {
    it.mapToWeatherModel()
  })

fun DailyForecastRemote.Forecast.Weather.mapToWeatherModel() =
  DailyForecastModel.Forecast.Weather(id = id, main = main, description = description)