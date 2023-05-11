package com.sara.travelagency.data.network

import com.sara.travelagency.data.model.BookingsResponse
import com.sara.travelagency.data.model.HotelResponse
import com.sara.travelagency.data.model.RoomResponse
import retrofit2.http.Query
import javax.inject.Inject

class TravelAgencyService @Inject constructor(private val api: ApiService){

    suspend fun hotelsInRange(dateCheckIn: String, dateCheckOut: String): List<BookingsResponse>{
        return api.hotelsInRange(dateCheckIn, dateCheckOut)
    }

    suspend fun lookUpRoom(city: String, capacity: String, price: String): List<RoomResponse> {
        return api.lookUpRoom(city, capacity, price)
    }
    suspend fun getHotelById(idHotel: String): HotelResponse{
        return api.getHotelById(idHotel)
    }

    suspend fun getRoomById(id: String): RoomResponse {
        return api.getRoomById(id)
    }
}