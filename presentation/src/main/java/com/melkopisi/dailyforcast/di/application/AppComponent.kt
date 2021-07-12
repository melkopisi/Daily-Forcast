package com.melkopisi.dailyforcast.di.application

import android.app.Application
import com.melkopisi.common.di.application.scope.ApplicationScope
import com.melkopisi.dailyforcast.di.application.module.SchedulersModule
import com.melkopisi.dailyforcast.di.presentation.fragment.FragmentSubComponent
import com.melkopisi.data.di.NetworkModule
import com.melkopisi.data.di.RepositoryModule
import com.melkopisi.data.di.RoomModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class, RepositoryModule::class, RoomModule::class, SchedulersModule::class])
interface AppComponent {

  fun getFragmentSubComponent(): FragmentSubComponent.Factory

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance application:Application): AppComponent
  }
}