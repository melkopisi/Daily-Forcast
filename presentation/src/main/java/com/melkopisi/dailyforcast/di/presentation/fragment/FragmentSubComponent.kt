package com.melkopisi.dailyforcast.di.presentation.fragment

import android.content.Context
import com.melkopisi.dailyforcast.di.presentation.fragment.module.FragmentModule
import com.melkopisi.dailyforcast.di.presentation.fragment.module.FragmentViewModelModule
import com.melkopisi.dailyforcast.di.presentation.scopes.PerFragment
import com.melkopisi.dailyforcast.features.forcast.fragments.ForecastFragment
import dagger.BindsInstance
import dagger.Subcomponent


@PerFragment
@Subcomponent(modules = [FragmentViewModelModule::class, FragmentModule::class])
interface FragmentSubComponent {

  fun inject(forecastFragment: ForecastFragment)

  @Subcomponent.Factory
  interface Factory {
    fun create(@BindsInstance application: Context): FragmentSubComponent
  }
}