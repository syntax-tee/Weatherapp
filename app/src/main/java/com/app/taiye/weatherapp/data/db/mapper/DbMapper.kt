
package com.app.taiye.weatherapp.data.db.mapper

import com.app.taiye.weatherapp.data.db.entities.DbForecast
import com.app.taiye.weatherapp.data.db.entities.DbLocationDetails
import com.app.taiye.weatherapp.domain.model.Forecast
import com.app.taiye.weatherapp.domain.model.LocationDetails

interface DbMapper {

  fun mapDomainLocationDetailsToDb(locationDetails: LocationDetails): DbLocationDetails

  fun mapDbLocationDetailsToDomain(locationDetails: DbLocationDetails): LocationDetails

  fun mapDomainForecastsToDb(forecasts: List<Forecast>): List<DbForecast>

  fun mapDbForecastsToDomain(forecasts: List<DbForecast>): List<Forecast>
}