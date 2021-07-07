package com.melkopisi.dailyforcast.features.forcast.mappers

import com.melkopisi.dailyforcast.features.forcast.models.DailyForecastUiModel
import com.melkopisi.domain.models.DailyForecastModel

fun DailyForecastModel.mapToDailyForecastUiModel() =
  DailyForecastUiModel(cod = cod, list.map { it.mapToInnerForecastUiModel() })

fun DailyForecastModel.Forecast.mapToInnerForecastUiModel() =
  DailyForecastUiModel.Forecast(weather = this.weather.map {
    it.mapToWeatherUiModel()
  })

fun DailyForecastModel.Forecast.Weather.mapToWeatherUiModel() =
  DailyForecastUiModel.Forecast.Weather(id = id, main = main, description = description)