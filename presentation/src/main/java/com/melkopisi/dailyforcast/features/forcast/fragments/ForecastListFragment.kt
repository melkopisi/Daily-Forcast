package com.melkopisi.dailyforcast.features.forcast.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.melkopisi.dailyforcast.App
import com.melkopisi.dailyforcast.databinding.FragmentForecastListBinding
import com.melkopisi.dailyforcast.di.presentation.fragment.FragmentSubComponent
import com.melkopisi.dailyforcast.di.presentation.viewmodel.ViewModelFactoryProvider
import com.melkopisi.dailyforcast.features.MainActivity
import com.melkopisi.dailyforcast.features.forcast.adapters.DailyForecastAdapter
import com.melkopisi.dailyforcast.features.forcast.models.DailyForecastUiModel
import com.melkopisi.dailyforcast.features.forcast.viewmodels.ForecastViewModel
import com.melkopisi.dailyforcast.general.Resource
import com.melkopisi.dailyforcast.general.ResourceState.ERROR
import com.melkopisi.dailyforcast.general.ResourceState.LOADING
import com.melkopisi.dailyforcast.general.ResourceState.LOCAL_SUCCESS
import com.melkopisi.dailyforcast.general.ResourceState.SUCCESS
import com.melkopisi.dailyforcast.general.extensions.hideKeyboard
import com.melkopisi.dailyforcast.general.extensions.makeSnackBar
import com.melkopisi.dailyforcast.general.extensions.onSearch
import javax.inject.Inject

class ForecastListFragment : Fragment() {
  private var binding: FragmentForecastListBinding? = null
  @Inject lateinit var viewModelFactoryProvider: ViewModelFactoryProvider

  private val viewModel: ForecastViewModel by viewModels { viewModelFactoryProvider }
  private val adapter by lazy { DailyForecastAdapter() }

  private val fragmentSubComponent: FragmentSubComponent by lazy {
    ((requireActivity().applicationContext) as App).appComponent.getFragmentSubComponent()
      .create(requireActivity())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    fragmentSubComponent.inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentForecastListBinding.inflate(inflater, container, false)
    return binding?.root
    }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupObservers()
    setupToolbar()
    setupRecyclerView()
    setupSearchView()
  }

  private fun setupToolbar() {
    (activity as MainActivity).setSupportActionBar(binding?.toolbar)
    binding?.appBarLayout?.setBackgroundColor(
      ContextCompat.getColor(requireActivity(), android.R.color.transparent)
    )
    (activity as MainActivity).supportActionBar?.title = ""
  }

  private fun setupRecyclerView() {
    binding?.forecastRecyclerview?.adapter = adapter
  }

  private fun setupSearchView() {
    binding?.search?.onSearch {
      viewModel.getDailyForecast(binding?.search?.text.toString())
      binding?.search?.hideKeyboard()
    }
  }

  private fun setupObservers() {
    viewModel.getDailyForecastLiveData.observe(viewLifecycleOwner, this::getDailyForecastObserver)
  }

  private fun getDailyForecastObserver(result: Resource<DailyForecastUiModel>) {
    when (result.state) {
      LOADING -> binding?.progressBar?.isVisible = true
      SUCCESS -> {
        binding?.progressBar?.isVisible = false
        result.data?.list?.let { adapter.setData(it) }

      }
      LOCAL_SUCCESS -> {
        binding?.progressBar?.isVisible = false
        result.data?.list?.let { adapter.setData(it) }
      }

      ERROR -> {
        binding?.progressBar?.isVisible = false
        binding?.root?.makeSnackBar(getString(result.id)) {
          viewModel.getDailyForecast(binding?.search?.text.toString())
        }
      }
    }
  }
}