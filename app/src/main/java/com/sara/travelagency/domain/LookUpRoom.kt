package com.sara.travelagency.domain

import com.sara.travelagency.data.TravelAgencyRepository
import com.sara.travelagency.domain.model.RoomItem
import javax.inject.Inject

class LookUpRoom @Inject constructor(
    private val repository: TravelAgencyRepository
){

    suspend operator fun invoke(city: String, capacity: String, price: String, dateCheckIn: String, dateCheckOut: String): List<RoomItem> = repository.lookUpRoom(city, capacity, price, dateCheckIn, dateCheckOut)
}