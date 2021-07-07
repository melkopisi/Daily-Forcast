package com.melkopisi.data.di

import android.app.Application
import androidx.room.Room
import com.melkopisi.common.di.application.scope.ApplicationScope
import com.melkopisi.data.local.ForecastDatabase
import dagger.Module
import dagger.Provides

@Module
class RoomModule {
  @Provides
  @ApplicationScope fun providesRoomDb(context: Application): ForecastDatabase =
    Room.databaseBuilder(context, ForecastDatabase::class.java, "forecast_db")
      .fallbackToDestructiveMigration().build()

  @Provides
  @ApplicationScope
  fun provideForecastDao(db: ForecastDatabase) = db.forecastDao()

}