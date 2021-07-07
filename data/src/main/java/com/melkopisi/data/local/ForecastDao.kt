package com.melkopisi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.melkopisi.data.local.models.DailyForecastEntity
import io.reactivex.Single

@Dao
interface ForecastDao {

    @Insert(onConflict = REPLACE)
    fun saveForecast(entity: DailyForecastEntity)

    @Query("SELECT * FROM daily_forecast")
    fun getForecast(): Single<DailyForecastEntity>
}

