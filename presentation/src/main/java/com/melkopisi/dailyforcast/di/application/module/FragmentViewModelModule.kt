package com.melkopisi.dailyforcast.di.application.module

import androidx.lifecycle.ViewModel
import com.melkopisi.dailyforcast.di.presentation.scopes.ViewModelKey
import com.melkopisi.dailyforcast.di.presentation.viewmodel.ViewModelProviderModule
import com.melkopisi.dailyforcast.features.forcast.viewmodels.ForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelProviderModule::class])
abstract class FragmentViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(ForecastViewModel::class)
  abstract fun bindsForecastViewModel(forecastViewModel: ForecastViewModel): ViewModel
}