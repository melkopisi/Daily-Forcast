package com.melkopisi.dailyforcast.di.application

import android.app.Application
import com.melkopisi.common.di.application.scope.ApplicationScope
import com.melkopisi.dailyforcast.di.presentation.fragment.FragmentSubComponent
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {

  fun getFragmentSubComponent(): FragmentSubComponent.Factory

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance application:Application): AppComponent
  }
}