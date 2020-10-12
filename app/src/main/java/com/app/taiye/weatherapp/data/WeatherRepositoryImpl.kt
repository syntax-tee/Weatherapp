
package com.app.taiye.weatherapp.data

import com.app.taiye.weatherapp.data.db.dao.ForecastDao
import com.app.taiye.weatherapp.data.db.mapper.DbMapper
import com.app.taiye.weatherapp.data.network.client.WeatherApiClient
import com.raywenderlich.android.data.network.mapper.ApiMapper
import com.app.taiye.weatherapp.domain.model.Location
import com.app.taiye.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val weatherApiClient: WeatherApiClient,
    private val apiMapper: ApiMapper,
    private val backgroundDispatcher: CoroutineDispatcher,
    private val forecastDao: ForecastDao,
    private val dbMapper: DbMapper
) : WeatherRepository {

  override suspend fun findLocation(location: String) = withContext(backgroundDispatcher) {
    try {
      weatherApiClient.findLocation(location)
          .map(apiMapper::mapApiLocationToDomain)
    } catch (error: Throwable) {
      emptyList<Location>()
    }
  }

  override suspend fun fetchLocationDetails(id: Int) = withContext(backgroundDispatcher) {
    val locationDetails = try {
      apiMapper.mapApiLocationDetailsToDomain(
          weatherApiClient.getLocationDetails(id)
      )
    } catch (error: Throwable) {
      null
    }

    if (locationDetails != null) {
      forecastDao.updateLocationDetails(dbMapper.mapDomainLocationDetailsToDb(locationDetails))
      forecastDao.updateForecasts(dbMapper.mapDomainForecastsToDb(locationDetails.forecasts))
    }
  }

    override fun getForecasts() =
        forecastDao
            .getForecasts()
            .map { dbMapper.mapDbForecastsToDomain(it) }

}