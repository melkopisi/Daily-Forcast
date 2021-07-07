package com.melkopisi.domain.usecases

import com.melkopisi.domain.models.DailyForecastModel
import com.melkopisi.domain.repositories.DailyForecastRepository
import com.melkopisi.domain.utils.RxImmediateSchedulerRule
import com.nhaarman.mockito_kotlin.any
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetDailyForecastUseCaseTest {
  @get:Rule var instantExecutorRule = RxImmediateSchedulerRule()

  @Mock
  lateinit var dailyForecastRepository: DailyForecastRepository

  @Mock
  lateinit var dailyForecastModel: DailyForecastModel

  @Test
  fun `AddExtrasUseCase invoke() with Extras Data will call completeProfile()`() {

    Mockito.`when`(dailyForecastRepository.getDailyForecast(any()))
      .thenReturn(Single.just(dailyForecastModel))

    val useCase = GetDailyForecastUseCase(dailyForecastRepository)
    val resultObserver = useCase("cairo").test()

    resultObserver.assertComplete()
    resultObserver.dispose()
  }
}