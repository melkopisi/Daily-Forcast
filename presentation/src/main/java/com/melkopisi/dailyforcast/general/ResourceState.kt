package com.melkopisi.dailyforcast.general

sealed class ResourceState {
  object LOADING : ResourceState()
  object SUCCESS : ResourceState()
  object LOCAL_SUCCESS : ResourceState()
  object ERROR : ResourceState()
}