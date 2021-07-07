package com.melkopisi.dailyforcast.features.forcast.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.melkopisi.dailyforcast.App
import com.melkopisi.dailyforcast.databinding.FragmentForecastBinding
import com.melkopisi.dailyforcast.di.presentation.fragment.FragmentSubComponent
import com.melkopisi.dailyforcast.di.presentation.viewmodel.ViewModelFactoryProvider
import com.melkopisi.dailyforcast.features.forcast.models.DailyForecastUiModel
import com.melkopisi.dailyforcast.features.forcast.viewmodels.ForecastViewModel
import com.melkopisi.dailyforcast.general.Resource
import com.melkopisi.dailyforcast.general.ResourceState
import com.melkopisi.dailyforcast.general.ResourceState.ERROR
import com.melkopisi.dailyforcast.general.ResourceState.LOADING
import com.melkopisi.dailyforcast.general.ResourceState.LOCAL_SUCCESS
import com.melkopisi.dailyforcast.general.ResourceState.SUCCESS
import timber.log.Timber
import javax.inject.Inject

class ForecastFragment : Fragment() {
  @Inject lateinit var fragmentForecastBinding: FragmentForecastBinding
  @Inject lateinit var viewModelFactoryProvider: ViewModelFactoryProvider

  private val viewModel: ForecastViewModel by viewModels { viewModelFactoryProvider }

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
  ): View {
    return fragmentForecastBinding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupObservers()
    viewModel.getDailyForecast("cairo")
  }

  private fun setupObservers() {
    viewModel.getDailyForecastLiveData.observe(viewLifecycleOwner, this::getDailyForecastObserver)
  }

  private fun getDailyForecastObserver(result: Resource<List<DailyForecastUiModel.Forecast>>) {
    when (result.state) {
      LOADING -> {
      }
      SUCCESS -> {
        Timber.d("daily forecast is %s", result.data?.get(0)?.weather?.first()?.main)
      }
      ERROR -> {
        Toast.makeText(requireContext(), getString(result.id), Toast.LENGTH_SHORT).show()
      }
      LOCAL_SUCCESS -> {
        Timber.d("daily local forecast is %s", result.data?.get(0)?.weather?.first()?.main)
      }
    }
  }
}