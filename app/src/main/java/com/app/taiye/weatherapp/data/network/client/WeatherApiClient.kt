
package com.app.taiye.weatherapp.data.network.client

import com.app.taiye.weatherapp.data.network.model.ApiLocation
import com.app.taiye.weatherapp.data.network.model.ApiLocationDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiClient {

  @GET("location/search/")
  suspend fun findLocation(@Query("query") location: String): List<ApiLocation>

  @GET("location/{woeid}/")
  suspend fun getLocationDetails(@Path("woeid") woeid: Int): ApiLocationDetails
}