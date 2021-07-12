package com.melkopisi.dailyforcast.di.presentation.fragment.module

import com.humansoftsolution.ugu.common.schedulers.qualifires.Background
import com.humansoftsolution.ugu.common.schedulers.qualifires.ForeGround
import com.melkopisi.dailyforcast.di.presentation.scopes.PerFragment
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SchedulersModule {

  @Provides
  @PerFragment
  @ForeGround
  fun providesForegroundScheduler(): Scheduler {
    return AndroidSchedulers.mainThread()
  }

  @Provides
  @PerFragment
  @Background
  fun providesBackgroundScheduler(): Scheduler {
    return Schedulers.io()
  }
}