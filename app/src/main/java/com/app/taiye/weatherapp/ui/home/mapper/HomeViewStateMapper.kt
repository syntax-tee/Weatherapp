
package com.app.taiye.weatherapp.ui.home.mapper

import com.app.taiye.weatherapp.domain.model.Forecast
import com.app.taiye.weatherapp.domain.model.Location
import com.app.taiye.weatherapp.ui.home.ForecastViewState
import com.app.taiye.weatherapp.ui.home.LocationViewState

interface HomeViewStateMapper {

  fun mapForecastsToViewState(forecasts: List<Forecast>): List<ForecastViewState>

  fun mapLocationsToViewState(locations: List<Location>): List<LocationViewState>
}