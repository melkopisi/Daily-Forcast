package com.melkopisi.dailyforcast.di.presentation.fragment

import android.content.Context
import com.melkopisi.dailyforcast.di.presentation.fragment.module.FragmentViewModelModule
import com.melkopisi.dailyforcast.di.presentation.scopes.PerFragment
import com.melkopisi.dailyforcast.features.forcast.fragments.ForecastListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [FragmentViewModelModule::class])
interface FragmentSubComponent {

  fun inject(forecastListFragment: ForecastListFragment)

  @Subcomponent.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): FragmentSubComponent
  }
}