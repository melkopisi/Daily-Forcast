package com.melkopisi.dailyforcast.general

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

abstract class BaseViewModel : ViewModel() {

  private val disposables = CompositeDisposable()

  fun Disposable.addDisposable() {
    this.addTo(disposables)
  }

  private fun dispose() {
    if (!disposables.isDisposed)
      disposables.dispose()
  }

  @CallSuper
  override fun onCleared() {
    super.onCleared()
    dispose()
  }
}