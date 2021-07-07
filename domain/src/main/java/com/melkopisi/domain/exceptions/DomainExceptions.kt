package com.melkopisi.domain.exceptions

import com.melkopisi.domain.models.DailyForecastModel

object NoDataException : Throwable()
class NoRemoteDataException(val data: DailyForecastModel) : Throwable()