package com.sara.travelagency.data.network

import com.sara.travelagency.data.model.BookingsResponse
import com.sara.travelagency.data.model.HotelResponse
import com.sara.travelagency.data.model.RoomResponse
import com.sara.travelagency.data.model.UserPut
import com.sara.travelagency.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("rooms/lookup")
    suspend fun lookUpRoom(@Query("city")city: String, @Query("capacity")capacity: String, @Query("price")price: String): Response<List<RoomResponse>>

    @GET("bookings/hotelsinrange")
    suspend fun hotelsInRange(@Query("dateCheckIn")dateCheckIn: String, @Query("dateCheckOut")dateCheckOut: String): Response<List<BookingsResponse>>

    @GET("bookings/userbookings")
    suspend fun getBookings(@Query("user_id")idUser: String): Response<List<BookingsResponse>>

    @GET("hotels/{hotel_id}")
    suspend fun getHotelById(@Path("hotel_id")idHotel: String): Response<HotelResponse>

    @GET("rooms/{room_id}")
    suspend fun getRoomById(@Path("room_id")id: String): Response<RoomResponse>

    @GET("users")
    suspend fun getAllUsers(): Response<List<UserResponse>>
    @GET("users/{user_id}")
    suspend fun getUserById(@Path("user_id")idUser: String): Response<UserResponse>

    @POST("users")
    suspend fun insertNewUser(@Body user: UserResponse)

    @PUT("users")
    suspend fun updateUser(@Body user: UserPut): Response<UserResponse> //todo returns null(?)


}