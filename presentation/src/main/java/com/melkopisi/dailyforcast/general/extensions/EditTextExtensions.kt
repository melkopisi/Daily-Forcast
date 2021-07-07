package com.melkopisi.dailyforcast.general.extensions

import android.content.Context
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.onSearch(callback: () -> Unit) {
  setOnEditorActionListener { _, actionId, _ ->
    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
      callback()
      return@setOnEditorActionListener true
    }
    return@setOnEditorActionListener false
  }
}

fun EditText.hideKeyboard() {
  val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
  imm?.hideSoftInputFromWindow(rootView?.windowToken, 0)
}