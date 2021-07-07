package com.melkopisi.dailyforcast.features.forcast.models

import androidx.annotation.Keep

data class DailyForecastUiModel(
  /* val cod: String,
   val message: Int,
   val cnt: Int,*/
  val list: List<Forecast>,
) {
  @Keep
  data class Forecast(
    /* val dt: Int,
     val main: Main,*/
    val weather: List<Weather>,
    /*   val clouds: Clouds,
       val wind: Wind,
       val visibility: Int,
       val pop: Int,
       val sys: Sys,
       val dtTxt: String*/
  ) {
    @Keep
    data class Weather(
      val id: Int,
      val main: String,
      val description: String,
    )
  }
}
