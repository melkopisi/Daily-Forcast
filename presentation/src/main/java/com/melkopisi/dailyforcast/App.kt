package com.melkopisi.dailyforcast

import android.app.Application
import com.melkopisi.dailyforcast.di.application.AppComponent
import com.melkopisi.dailyforcast.di.application.DaggerAppComponent
import timber.log.Timber

class App : Application() {
  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
    setupInjection()
  }

  private fun setupInjection() {
    appComponent = DaggerAppComponent.factory().create(this)
  }
}