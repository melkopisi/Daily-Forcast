package com.melkopisi.data.remote.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DailyForecastRemote(
    @SerializedName("cod")
    val cod: String,
    @SerializedName("message")
    val message: Int,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<Forecast>,
    @SerializedName("city")
    val city: City
) {
    @Keep
    data class Forecast (
        @SerializedName("dt")
        val dt: Int,
        @SerializedName("main")
        val main: Main,
        @SerializedName("weather")
        val weather: List<Weather>,
        @SerializedName("clouds")
        val clouds: Clouds,
        @SerializedName("wind")
        val wind: Wind,
        @SerializedName("visibility")
        val visibility: Int,
        @SerializedName("pop")
        val pop: Int,
        @SerializedName("sys")
        val sys: Sys,
        @SerializedName("dt_txt")
        val dtTxt: String
    ) {
        @Keep
        data class Main(
            @SerializedName("temp")
            val temp: Double,
            @SerializedName("feels_like")
            val feelsLike: Double,
            @SerializedName("temp_min")
            val tempMin: Double,
            @SerializedName("temp_max")
            val tempMax: Double,
            @SerializedName("pressure")
            val pressure: Int,
            @SerializedName("sea_level")
            val seaLevel: Int,
            @SerializedName("grnd_level")
            val grndLevel: Int,
            @SerializedName("humidity")
            val humidity: Int,
            @SerializedName("temp_kf")
            val tempKf: Double
        )

        @Keep
        data class Weather(
            @SerializedName("id")
            val id: Int,
            @SerializedName("main")
            val main: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("icon")
            val icon: String
        )

        @Keep
        data class Clouds(
            @SerializedName("all")
            val all: Int
        )

        @Keep
        data class Wind(
            @SerializedName("speed")
            val speed: Double,
            @SerializedName("deg")
            val deg: Int,
            @SerializedName("gust")
            val gust: Double
        )

        @Keep
        data class Sys(
            @SerializedName("pod")
            val pod: String
        )
    }

    @Keep
    data class City(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("coord")
        val coord: Coord,
        @SerializedName("country")
        val country: String,
        @SerializedName("population")
        val population: Int,
        @SerializedName("timezone")
        val timezone: Int,
        @SerializedName("sunrise")
        val sunrise: Int,
        @SerializedName("sunset")
        val sunset: Int
    ) {
        @Keep
        data class Coord(
            @SerializedName("lat")
            val lat: Double,
            @SerializedName("lon")
            val lon: Double
        )
    }
}