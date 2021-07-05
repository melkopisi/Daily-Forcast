package com.melkopisi.dailyforcast.features.forcast.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.melkopisi.dailyforcast.App
import com.melkopisi.dailyforcast.databinding.FragmentForecastBinding
import com.melkopisi.dailyforcast.di.presentation.fragment.FragmentSubComponent
import javax.inject.Inject

class ForecastFragment : Fragment() {
  @Inject lateinit var fragmentForecastBinding: FragmentForecastBinding

  private val fragmentSubComponent: FragmentSubComponent by lazy {
    ((requireActivity().applicationContext) as App).appComponent.getFragmentSubComponent()
      .create(requireContext())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    fragmentSubComponent.inject(this)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return fragmentForecastBinding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }
}