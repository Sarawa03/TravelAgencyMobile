package com.sara.travelagency.domain

import com.sara.travelagency.data.TravelAgencyRepository
import com.sara.travelagency.domain.model.RoomItem
import javax.inject.Inject

class BuyHotelRoom @Inject constructor(private val repository: TravelAgencyRepository) {
    suspend operator fun invoke(room:RoomItem, dateCheckIn: String, dateCheckOut: String) = repository.buyHotelRoom(room, dateCheckIn, dateCheckOut)
}