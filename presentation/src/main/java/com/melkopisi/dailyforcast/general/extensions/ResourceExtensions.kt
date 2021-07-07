package com.melkopisi.dailyforcast.general.extensions

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.melkopisi.dailyforcast.general.Resource
import com.melkopisi.dailyforcast.general.ResourceState.ERROR
import com.melkopisi.dailyforcast.general.ResourceState.LOADING
import com.melkopisi.dailyforcast.general.ResourceState.LOCAL_SUCCESS
import com.melkopisi.dailyforcast.general.ResourceState.SUCCESS

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) {
  value = Resource(SUCCESS, data)
}

fun <T> MutableLiveData<Resource<T>>.setLocalSuccess(data: T) {
  value = Resource(LOCAL_SUCCESS, data)
}

fun <T> MutableLiveData<Resource<T>>.setLoading() {
  value = (Resource(LOADING))
}

fun <T> MutableLiveData<Resource<T>>.setError(@StringRes id: Int) {
  value = Resource(ERROR, id = id)
}
