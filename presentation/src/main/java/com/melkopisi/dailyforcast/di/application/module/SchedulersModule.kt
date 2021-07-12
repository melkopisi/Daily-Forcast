package com.melkopisi.dailyforcast.di.application.module

import com.melkopisi.common.di.application.scope.ApplicationScope
import com.melkopisi.dailyforcast.di.application.qualifires.Background
import com.melkopisi.dailyforcast.di.application.qualifires.ForeGround
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SchedulersModule {

  @Provides
  @ApplicationScope
  @ForeGround
  fun providesForegroundScheduler(): Scheduler {
    return AndroidSchedulers.mainThread()
  }

  @Provides
  @ApplicationScope
  @Background
  fun providesBackgroundScheduler(): Scheduler {
    return Schedulers.io()
  }
}