package com.sara.travelagency.data

import android.util.Log
import com.sara.travelagency.data.model.UserResponse
import com.sara.travelagency.data.network.TravelAgencyService
import com.sara.travelagency.domain.model.BookingItem
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.domain.model.UserItem
import com.sara.travelagency.domain.model.toDomain
import javax.inject.Inject

class TravelAgencyRepository @Inject constructor(
    private val api: TravelAgencyService
){
    suspend fun lookUpRoom(city: String, capacity: String, price: String, dateCheckIn: String, dateCheckOut: String): List<RoomItem> {

        val bookings = api.hotelsInRange(dateCheckIn, dateCheckOut).map {it.toDomain()}
        
        val rooms = api.lookUpRoom(city, capacity, price).map {it.toDomain(api.getHotelById(it.hotel).toDomain())}

        return getUnbookedRooms(bookings, rooms)
    }

    private fun getUnbookedRooms(bookings: List<BookingItem>, rooms: List<RoomItem>): List<RoomItem> {
        val unbookedRooms = rooms.toMutableSet()
        rooms.map { room ->
            bookings.map { booking ->
                if(booking.idRoom==room.idRoom){//Esta ocupada
                    unbookedRooms.remove(room)
                }
            }
        }
        return unbookedRooms.toList()
    }

    suspend fun getRoomById(id: String): RoomItem {
        val roomResponse = api.getRoomById(id)
        return api.getRoomById(id).toDomain(api.getHotelById(roomResponse.hotel).toDomain())
    }

    suspend fun logInUser(username: String, password: String): UserItem?{

        val listUsers = api.getAllUsers()
        Log.i("POTATO",listUsers.toString())
        Log.i("POTATO",isCorrectCredentials(listUsers, username, password).toString())

        return isCorrectCredentials(listUsers, username, password)
    }
    fun isCorrectCredentials(listUsers: List<UserResponse>, username: String, password: String): UserItem?{
        val listUsersItem = listUsers.map { it.toDomain() }

        listUsersItem.forEach {
            if(it.username == username && it.userPassword == password) return it
        }
        return null
    }


}