package com.melkopisi.dailyforcast.general

import androidx.annotation.StringRes
import com.melkopisi.dailyforcast.R

class Resource<out T> constructor(
  val state: ResourceState,
  val data: T? = null,
  @StringRes val id: Int = R.string.general_error
)