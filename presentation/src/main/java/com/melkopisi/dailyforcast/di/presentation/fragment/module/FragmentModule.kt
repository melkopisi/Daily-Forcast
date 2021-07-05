package com.melkopisi.dailyforcast.di.presentation.fragment.module

import android.content.Context
import android.view.LayoutInflater
import com.melkopisi.dailyforcast.databinding.FragmentForecastBinding
import com.melkopisi.dailyforcast.di.presentation.scopes.PerFragment
import dagger.Module
import dagger.Provides

@Module
abstract class FragmentModule {
  companion object {
    @Provides
    @PerFragment
    fun providesLayoutInflater(context: Context): LayoutInflater {
      return LayoutInflater.from(context)
    }

    @Provides
    @PerFragment
    fun providesFragmentForecastBinding(inflater: LayoutInflater): FragmentForecastBinding {
      return FragmentForecastBinding.inflate(inflater)
    }
  }
}