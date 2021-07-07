package com.melkopisi.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.melkopisi.data.local.models.DailyForecastEntity
import com.melkopisi.data.remote.models.DailyForecastRemote

/*class DailyForecastInnerWeatherRemoteConverter {
  @TypeConverter
  fun fromJson(value: String) =
    Gson().fromJson(value, DailyForecastRemote.Forecast.Weather::class.java)

  @TypeConverter
  fun toJson(value: DailyForecastRemote.Forecast.Weather) = Gson().toJson(value)
}

class DailyForecastInnerForecastRemoteConverter {
  @TypeConverter
  fun fromJson(value: String) =
    Gson().fromJson(value, DailyForecastRemote.Forecast::class.java)

  @TypeConverter
  fun toJson(value: DailyForecastRemote.Forecast) = Gson().toJson(value)
}

class DailyForecastRemoteConverter {
  @TypeConverter
  fun fromJson(value: String) =
    Gson().fromJson(value, DailyForecastRemote::class.java)

  @TypeConverter
  fun toJson(value: DailyForecastRemote) = Gson().toJson(value)
}*/

class DailyForecastInnerForecastList {
  @TypeConverter
  fun fromJson(value: String?): List<DailyForecastEntity.Forecast> {
    val listType = object : TypeToken<List<DailyForecastEntity.Forecast>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun toJson(value: List<DailyForecastEntity.Forecast>): String = Gson().toJson(value)
}

class DailyForecastInnerWeatherList {
  @TypeConverter
  fun fromJson(value: String): List<DailyForecastEntity.Forecast.Weather> {
    val listType = object : TypeToken<List<DailyForecastEntity.Forecast.Weather>>() {}.type
    return Gson().fromJson(value, listType)
  }

  @TypeConverter
  fun toJson(value: List<DailyForecastEntity.Forecast.Weather>): String =
    Gson().toJson(value)
}
