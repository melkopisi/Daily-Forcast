package com.melkopisi.dailyforcast.di.application

import android.app.Application
import com.melkopisi.data.di.NetworkModule
import com.melkopisi.data.di.RepositoryModule
import dagger.Module

@Module(includes = [NetworkModule::class, RepositoryModule::class])
class AppModule {
}