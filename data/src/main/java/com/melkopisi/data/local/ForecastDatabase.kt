package com.melkopisi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.melkopisi.data.local.models.DailyForecastEntity
import javax.inject.Inject

@Database(entities = [DailyForecastEntity::class], version = 1, exportSchema = false)
@TypeConverters(
  DailyForecastInnerForecastList::class,
  DailyForecastInnerWeatherList::class
)
abstract class ForecastDatabase: RoomDatabase() {
  abstract fun forecastDao(): ForecastDao
}