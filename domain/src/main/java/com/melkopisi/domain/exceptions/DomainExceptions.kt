package com.melkopisi.domain.exceptions

object NoDataException : Throwable()
class NoRemoteDataException(val data: Any) : Throwable()