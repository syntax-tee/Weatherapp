
package com.app.taiye.weatherapp.data.network.mapper

import com.raywenderlich.android.data.network.model.ApiLocation
import com.raywenderlich.android.data.network.model.ApiLocationDetails
import com.app.taiye.weatherapp.domain.model.Location
import com.app.taiye.weatherapp.domain.model.LocationDetails

interface ApiMapper {

  fun mapApiLocationToDomain(apiLocation: ApiLocation): Location

  fun mapApiLocationDetailsToDomain(apiLocationDetails: ApiLocationDetails): LocationDetails
}