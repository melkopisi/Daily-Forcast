package com.melkopisi.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_forecast")
data class DailyForecastEntity(
  @PrimaryKey(autoGenerate = false)
  @ColumnInfo(name = "daily_forecast_id") val id: Long = 0L,
  @ColumnInfo(name = "daily_forecast_code") val cod: String,
  @ColumnInfo(name = "daily_forecast_list") val list: List<Forecast>,
) {
  data class Forecast(
    @ColumnInfo(name = "daily_forecast_weather_list") val weather: List<Weather>,
  ) {

    data class Weather(
      @ColumnInfo(name = "daily_forecast_weather_id") val id: Int,
      @ColumnInfo(name = "daily_forecast_weather_status") val main: String,
      @ColumnInfo(name = "daily_forecast_weather_desc") val description: String,
    )
  }
}