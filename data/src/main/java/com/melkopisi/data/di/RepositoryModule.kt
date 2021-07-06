package com.melkopisi.data.di

import com.melkopisi.common.di.application.scope.ApplicationScope
import com.melkopisi.data.DailyForecastRepositoryImpl
import com.melkopisi.domain.repositories.DailyForecastRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

  @Binds
  @ApplicationScope abstract fun bindDailyForecastRepository(
    dailyForecastRepositoryImpl: DailyForecastRepositoryImpl
  ): DailyForecastRepository
}