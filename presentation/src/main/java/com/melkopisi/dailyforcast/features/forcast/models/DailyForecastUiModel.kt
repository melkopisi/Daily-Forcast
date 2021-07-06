package com.melkopisi.dailyforcast.features.forcast.models

import androidx.annotation.Keep

data class DailyForecastUiModel(
  /* val cod: String,
   val message: Int,
   val cnt: Int,*/
  val list: List<Forecast>,
//  val city: City
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
/*    @Keep
    data class Main(
      val temp: Double,
      val feelsLike: Double,
      val tempMin: Double,
      val tempMax: Double,
      val pressure: Int,
      val seaLevel: Int,
      val grndLevel: Int,
      val humidity: Int,
      val tempKf: Double
    )*/

    @Keep
    data class Weather(
      val id: Int,
      val main: String,
      val description: String,
//      val icon: String
    )

    /* @Keep
     data class Clouds(
       val all: Int
     )

     @Keep
     data class Wind(
       val speed: Double,
       val deg: Int,
       val gust: Double
     )

     @Keep
     data class Sys(
       val pod: String
     )
   }

   @Keep
   data class City(
     val id: Int,
     val name: String,
     val coord: Coord,
     val country: String,
     val population: Int,
     val timezone: Int,
     val sunrise: Int,
     val sunset: Int
   ) {
     @Keep
     data class Coord(
       val lat: Double,
       val lon: Double
     )*/
  }
}
