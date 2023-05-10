package com.sara.travelagency.data.network

import com.sara.travelagency.data.model.BookingsResponse
import com.sara.travelagency.data.model.HotelResponse
import com.sara.travelagency.data.model.RoomResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("rooms/lookup")
    suspend fun lookUpRoom(@Query("city")city: String, @Query("capacity")capacity: String, @Query("price")price: String): List<RoomResponse>

    @GET("bookings/hotelsinrange")
    suspend fun hotelsInRange(@Query("dateCheckIn")dateCheckIn: String, @Query("dateCheckOut")dateCheckOut: String): List<BookingsResponse>

    @GET("hotels/{hotel_id}")
    suspend fun getHotelById(@Path("hotel_id")idHotel: String): HotelResponse

}