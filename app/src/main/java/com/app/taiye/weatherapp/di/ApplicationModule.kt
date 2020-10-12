
package com.app.taiye.weatherapp.di

import com.app.taiye.weatherapp.data.WeatherRepositoryImpl
import com.app.taiye.weatherapp.data.db.ForecastDatabase
import com.app.taiye.weatherapp.data.db.mapper.DbMapper
import com.app.taiye.weatherapp.data.db.mapper.DbMapperImpl
import com.app.taiye.weatherapp.data.network.client.WeatherApiClient
import com.app.taiye.weatherapp.data.network.mapper.ApiMapper
import com.app.taiye.weatherapp.data.network.mapper.ApiMapperImpl
import com.app.taiye.weatherapp.domain.repository.WeatherRepository
import com.app.taiye.weatherapp.ui.home.mapper.HomeViewStateMapper
import com.app.taiye.weatherapp.ui.home.mapper.HomeViewStateMapperImpl
import com.app.taiye.weatherapp.util.image_loader.ImageLoader
import com.app.taiye.weatherapp.util.image_loader.ImageLoaderImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL_NAME = "BASE_URL"
private const val BASE_URL = "https://www.metaweather.com/api/"
private const val MAIN_DISPATCHER = "main_dispatcher"
private const val BACKGROUND_DISPATCHER = "background_dispatcher"

val applicationModule = module {
  single(named(BASE_URL_NAME)) {
    BASE_URL
  }

  single {
    HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
  }

  single {
    val client = OkHttpClient().newBuilder()

    if (BuildConfig.DEBUG) {
      client.addInterceptor(get<HttpLoggingInterceptor>())
    }

    client.build()
  }

  single {
    Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()
  }

  single {
    Retrofit.Builder()
      .baseUrl(get<String>(named(BASE_URL_NAME)))
      .addConverterFactory(MoshiConverterFactory.create(get()))
      .client(get())
      .build()
  }

  single {
    get<Retrofit>().create(WeatherApiClient::class.java)
  }

  single<ApiMapper> {
    ApiMapperImpl()
  }

  single<WeatherRepository> {
    WeatherRepositoryImpl(
      get(),
      get(),
      get(named(BACKGROUND_DISPATCHER)),
      get(),
      get()
    )
  }

  single<ImageLoader> { ImageLoaderImpl(get()) }

  single { Picasso.get() }

  single<HomeViewStateMapper> { HomeViewStateMapperImpl() }

  single(named(MAIN_DISPATCHER)) { Dispatchers.Main }

  single(named(BACKGROUND_DISPATCHER)) { Dispatchers.IO }

  single { ForecastDatabase.create(androidContext()) }

  single { get<ForecastDatabase>().forecastDao() }

  single<DbMapper> { DbMapperImpl() }
}
