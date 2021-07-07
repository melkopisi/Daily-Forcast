package com.melkopisi.data

import com.google.gson.Gson
import com.melkopisi.data.local.ForecastDao
import com.melkopisi.data.remote.DailyForecastApi
import com.melkopisi.domain.repositories.DailyForecastRepository
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class DailyForecastRepositoryImplTest {
  private val server: MockWebServer = MockWebServer()
  private val MOCK_WEBSERVER_PORT = 8000
  lateinit var dailyForecastApi: DailyForecastApi
  lateinit var jsonRepository: DailyForecastRepository
  @Mock lateinit var forecastDao: ForecastDao

  @Before
  fun setup() {
    server.start(MOCK_WEBSERVER_PORT)
    dailyForecastApi = Retrofit.Builder()
      .baseUrl(server.url("/"))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(Gson()))
      .build()
      .create(DailyForecastApi::class.java)
    jsonRepository = DailyForecastRepositoryImpl(dailyForecastApi, forecastDao)
  }

  @After
  fun shutdown() {
    server.shutdown()
  }

  @Test
  fun `getDailyForecast API parse success`() {
    server.apply {
      enqueue(
        MockResponse().setBody(
          MockResponseFileReader(
            "features/dailyForecast/DailyForecastResponseSuccess.json"
          ).content
        )
      )
    }
    jsonRepository.getDailyForecast("cairo")
      .test()
      .awaitDone(3, TimeUnit.SECONDS)
      .assertComplete()
      .assertValue { it.list[0].weather[0].main == "Clear" }
  }
}