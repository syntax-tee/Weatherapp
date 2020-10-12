
package com.app.taiye.weatherapp.domain.repository

import com.app.taiye.weatherapp.domain.model.Forecast
import com.app.taiye.weatherapp.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

  suspend fun findLocation(location: String): List<Location>

  suspend fun fetchLocationDetails(id: Int)

  fun getForecasts(): Flow<List<Forecast>>


}