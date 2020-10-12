
package com.app.taiye.weatherapp.application

import android.app.Application
import com.app.taiye.weatherapp.di.applicationModule
import com.app.taiye.weatherapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    initKoin()
  }

  private fun initKoin() {
    startKoin {
      androidLogger()
      androidContext(this@WeatherApplication)
      modules(listOf(applicationModule, presentationModule))
    }
  }
}