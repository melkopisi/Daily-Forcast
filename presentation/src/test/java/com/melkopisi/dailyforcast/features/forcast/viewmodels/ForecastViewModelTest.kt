package com.melkopisi.dailyforcast.features.forcast.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.melkopisi.dailyforcast.features.forcast.models.DailyForecastUiModel
import com.melkopisi.dailyforcast.general.Resource
import com.melkopisi.dailyforcast.general.ResourceState
import com.melkopisi.domain.exceptions.NoDataException
import com.melkopisi.domain.models.DailyForecastModel
import com.melkopisi.domain.usecases.GetDailyForecastUseCase
import com.melkopisi.domain.utils.RxImmediateSchedulerRule
import com.nhaarman.mockito_kotlin.any
import io.reactivex.Single
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ForecastViewModelTest {
  @get:Rule var instantExecutorRule = InstantTaskExecutorRule()
  @get:Rule var rxImmediateSchedulerRule = RxImmediateSchedulerRule()

  private lateinit var forecastViewModel: ForecastViewModel

  @Mock lateinit var getDailyForecastUseCase: GetDailyForecastUseCase

  @Mock lateinit var getDailyForecastLiveData: Observer<Resource<DailyForecastUiModel>>

  @Captor lateinit var getDailyForecastCaptor: ArgumentCaptor<Resource<DailyForecastUiModel>>

  @Before
  fun setup() {
    forecastViewModel = ForecastViewModel(getDailyForecastUseCase)
  }

  @After
  fun tearDown() {
    forecastViewModel.getDailyForecastLiveData.removeObserver(getDailyForecastLiveData)
  }

  @Test
  fun `when getDailyForecast() then return success`() {

    forecastViewModel.getDailyForecastLiveData.observeForever(getDailyForecastLiveData)

    val innerForecastModel: DailyForecastModel.Forecast =
      Mockito.mock(DailyForecastModel.Forecast::class.java)
    val model = DailyForecastModel("200", listOf(innerForecastModel))
    Mockito.lenient()
      .`when`(getDailyForecastUseCase(any()))
      .thenReturn(Single.just(model))

    forecastViewModel.getDailyForecast("cairo")

    Mockito.verify(getDailyForecastLiveData, Mockito.times(2))
      .onChanged(getDailyForecastCaptor.capture())

    val values = getDailyForecastCaptor.allValues
    Assert.assertEquals(ResourceState.LOADING, values[0].state)
    Assert.assertEquals(ResourceState.SUCCESS, values[1].state)
    Assert.assertEquals("200", values[1].data?.cod)
  }

  @Test
  fun `when getDailyForecast() then return error`() {

    forecastViewModel.getDailyForecastLiveData.observeForever(getDailyForecastLiveData)

    val exception = NoDataException
    Mockito.lenient()
      .`when`(getDailyForecastUseCase(any()))
      .thenReturn(Single.error(exception))

    forecastViewModel.getDailyForecast("cairo")

    Mockito.verify(getDailyForecastLiveData, Mockito.times(2))
      .onChanged(getDailyForecastCaptor.capture())
    val values = getDailyForecastCaptor.allValues
    Assert.assertEquals(ResourceState.LOADING, values[0].state)
    Assert.assertEquals(ResourceState.ERROR, values[1].state)
  }

}