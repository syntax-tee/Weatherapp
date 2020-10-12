
package com.app.taiye.weatherapp.di

import com.app.taiye.weatherapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
  viewModel { HomeViewModel(get(), get()) }
}
