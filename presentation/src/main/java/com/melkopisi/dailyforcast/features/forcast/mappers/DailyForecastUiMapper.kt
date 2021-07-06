package com.melkopisi.dailyforcast.features.forcast.mappers

import com.melkopisi.dailyforcast.features.forcast.models.DailyForecastUiModel
import com.melkopisi.domain.models.DailyForecastModel.Forecast
import com.melkopisi.domain.models.DailyForecastModel.Forecast.Weather

fun Forecast.mapToDailyForecastUiModel() =
  DailyForecastUiModel.Forecast(weather = this.weather.map {
    it.mapToWeatherUiModel()
  })

fun Weather.mapToWeatherUiModel() =
  DailyForecastUiModel.Forecast.Weather(id = id, main = main, description = description)