package com.melkopisi.domain.models

import androidx.annotation.Keep

data class DailyForecastModel(
   val cod: String,
  val list: List<Forecast>,
) {
  @Keep
  data class Forecast(
    val weather: List<Weather>,
  ) {

    @Keep
    data class Weather(
      val id: Int,
      val main: String,
      val description: String,
    )
  }
}
