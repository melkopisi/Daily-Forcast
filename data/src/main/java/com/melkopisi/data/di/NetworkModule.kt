package com.melkopisi.data.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.melkopisi.common.di.application.scope.ApplicationScope
import com.melkopisi.data.BuildConfig
import com.melkopisi.data.remote.DailyForecastApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
  @Provides
  @ApplicationScope fun providesGson(): Gson = GsonBuilder().setLenient().create()

  @Provides
  @ApplicationScope fun providesInterceptor(): LoggingInterceptor =
    LoggingInterceptor.Builder().setLevel(Level.BASIC).log(Log.VERBOSE).build()

  @Provides
  @ApplicationScope fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .client(okHttpClient)
      .build()
  }

  @Provides
  @ApplicationScope fun providesOkHttpClient(interceptor: LoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(60, TimeUnit.SECONDS)
      .addInterceptor(interceptor)
      .build()
  }

  @Provides
  @ApplicationScope fun providesDailyForecastApi(retrofit: Retrofit): DailyForecastApi {
    return retrofit.create(DailyForecastApi::class.java)
  }
}