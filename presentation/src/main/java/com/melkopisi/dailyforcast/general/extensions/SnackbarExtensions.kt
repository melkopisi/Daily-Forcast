package com.melkopisi.dailyforcast.general.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.melkopisi.dailyforcast.R

fun View.makeSnackBar(
  message: String,
  onClick: () -> Unit
) {
  val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
  snackBar.apply {
    setAction(this.context.getString(R.string.retry)) { onClick() }
    show()
  }
}