

package com.app.taiye.weatherapp.data.network.mapper

import com.app.taiye.weatherapp.data.network.model.ApiForecast
import com.app.taiye.weatherapp.data.network.model.ApiLocation
import com.app.taiye.weatherapp.data.network.model.ApiLocationDetails
import com.app.taiye.weatherapp.domain.model.Forecast
import com.app.taiye.weatherapp.domain.model.Location
import com.app.taiye.weatherapp.domain.model.LocationDetails

class ApiMapperImpl : ApiMapper {

  override fun mapApiLocationDetailsToDomain(apiLocationDetails: ApiLocationDetails): LocationDetails {
    return with(apiLocationDetails) {
      LocationDetails(
        forecasts.map { mapApiForecastToDomain(it) },
        time,
        sunrise,
        sunset,
        title,
        id
      )
    }
  }

  override fun mapApiLocationToDomain(apiLocation: ApiLocation) = Location(
    apiLocation.id,
    apiLocation.title
  )

  private fun mapApiForecastToDomain(apiForecast: ApiForecast) = with(apiForecast) {
    Forecast(
      id,
      weatherState,
      windDirection,
      date,
      minTemp,
      maxTemp,
      temp,
      windSpeed,
      airPressure,
      humidity,
      visibility,
      predictability,
      weatherStateAbbreviation
    )
  }
}
