package com.melkopisi.dailyforcast.di.application

import com.melkopisi.data.di.NetworkModule
import com.melkopisi.data.di.RepositoryModule
import com.melkopisi.data.di.RoomModule
import dagger.Module

@Module(includes = [NetworkModule::class, RepositoryModule::class, RoomModule::class])
class AppModule {
}