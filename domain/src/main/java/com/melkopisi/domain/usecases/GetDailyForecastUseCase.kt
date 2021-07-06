package com.melkopisi.domain.usecases

import com.melkopisi.domain.exceptions.NoDataException
import com.melkopisi.domain.models.DailyForecastModel
import com.melkopisi.domain.repositories.DailyForecastRepository
import io.reactivex.Single
import javax.inject.Inject

class GetDailyForecastUseCase @Inject constructor(private val dailyForecastRepository: DailyForecastRepository) {

  operator fun invoke(cityName: String): Single<List<DailyForecastModel.Forecast>> =
    dailyForecastRepository.getDailyForecast(cityName)
}