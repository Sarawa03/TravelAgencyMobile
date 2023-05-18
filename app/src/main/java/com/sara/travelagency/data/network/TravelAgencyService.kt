package com.sara.travelagency.data.network

import android.util.Log
import com.sara.travelagency.data.model.BookingsResponse
import com.sara.travelagency.data.model.HotelResponse
import com.sara.travelagency.data.model.RoomResponse
import com.sara.travelagency.data.model.UserPut
import com.sara.travelagency.data.model.UserResponse
import com.sara.travelagency.domain.model.BookingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query
import javax.inject.Inject

class TravelAgencyService @Inject constructor(private val api: ApiService){

    suspend fun hotelsInRange(dateCheckIn: String, dateCheckOut: String): List<BookingsResponse>{
        return api.hotelsInRange(dateCheckIn, dateCheckOut).body()?: emptyList()
    }

    suspend fun lookUpRoom(city: String, capacity: String, price: String): List<RoomResponse> {
        return api.lookUpRoom(city, capacity, price).body()?: emptyList()
    }
    suspend fun getHotelById(idHotel: String): HotelResponse{
        return withContext(Dispatchers.IO){
            api.getHotelById(idHotel).body()!!
        }
    }

    suspend fun getRoomById(id: String): RoomResponse {
        return withContext(Dispatchers.IO){
            api.getRoomById(id).body()!!
        }
    }

    suspend fun getAllUsers(): List<UserResponse> {
        return api.getAllUsers().body()?: emptyList()
    }

    suspend fun getUserById(id: String): UserResponse {
        return withContext(Dispatchers.IO){
            api.getUserById(id).body()!!
        }
    }

    suspend fun insertNewUser(user: UserResponse){
        return api.insertNewUser(user)
    }

    suspend fun updateUser(user:UserPut){
        api.updateUser(user)

    }

    suspend fun getBookings(idUser: String): List<BookingsResponse> {
        return api.getBookings(idUser).body()?: emptyList()
    }

    suspend fun buyHotelRoom(hotelRoom: BookingsResponse) {
        api.buyHotelRoom(hotelRoom)
    }


}