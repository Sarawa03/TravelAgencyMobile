package com.sara.travelagency.data

import com.sara.travelagency.data.network.TravelAgencyService
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.domain.model.toDomain
import javax.inject.Inject

class TravelAgencyRepository @Inject constructor(
    private val api: TravelAgencyService
){
    suspend fun lookUpRoom(city: String, capacity: String, price: String): List<RoomItem> {
        //TODO, MAP LIST
        return api.lookUpRoom(city, capacity, price).map { it.toDomain(api.getHotelById(it.hotel).toDomain()) }
    }
}