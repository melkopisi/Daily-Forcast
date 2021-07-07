package com.melkopisi.data.local.mappers

import com.melkopisi.data.local.models.DailyForecastEntity
import com.melkopisi.data.remote.models.DailyForecastRemote
import com.melkopisi.domain.models.DailyForecastModel

fun DailyForecastRemote.mapToDailyForecastEntity() =
  DailyForecastEntity(cod = cod, list = list.map { it.mapToInnerForecastEntityModel() })

fun DailyForecastRemote.Forecast.mapToInnerForecastEntityModel() =
  DailyForecastEntity.Forecast(weather = weather.map { it.mapToInnerWeatherEntityModel() })

fun DailyForecastRemote.Forecast.Weather.mapToInnerWeatherEntityModel() =
  DailyForecastEntity.Forecast.Weather(id = id, main = main, description = description)

fun DailyForecastEntity.mapToDailyForecastModel() =
  DailyForecastModel(cod = cod, list = list.map { it.mapToInnerForecastModel() })

fun DailyForecastEntity.Forecast.mapToInnerForecastModel() =
  DailyForecastModel.Forecast(weather = weather.map { it.mapToInnerWeatherModel() })

fun DailyForecastEntity.Forecast.Weather.mapToInnerWeatherModel() =
  DailyForecastModel.Forecast.Weather(id = id, main = main, description = description)