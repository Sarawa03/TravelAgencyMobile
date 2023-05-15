package com.sara.travelagency.data

import android.util.Log
import com.sara.travelagency.data.model.UserResponse
import com.sara.travelagency.data.model.toDomain
import com.sara.travelagency.data.model.toPut
import com.sara.travelagency.data.network.TravelAgencyService
import com.sara.travelagency.domain.model.BookingItem
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.domain.model.UserItem
import com.sara.travelagency.domain.model.toDomain
import com.sara.travelagency.ui.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TravelAgencyRepository @Inject constructor(
    private val api: TravelAgencyService
){
    suspend fun lookUpRoom(city: String, capacity: String, price: String, dateCheckIn: String, dateCheckOut: String): List<RoomItem> {

        val bookings = api.hotelsInRange(dateCheckIn, dateCheckOut).map {it.toDomain(api.getUserById(it.idUser).toDomain(), getRoomById(it.idRoom))}
        
        val rooms = api.lookUpRoom(city, capacity, price).map {it.toDomain(api.getHotelById(it.hotel).toDomain())}

        return getUnbookedRooms(bookings, rooms)
    }

    private fun getUnbookedRooms(bookings: List<BookingItem>, rooms: List<RoomItem>): List<RoomItem> {
        val unbookedRooms = rooms.toMutableSet()
        rooms.map { room ->
            bookings.map { booking ->
                if(booking.room.idRoom ==room.idRoom){//Esta ocupada
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

    suspend fun signUp(email: String, username: String, password: String):Boolean {
        val listUsers = api.getAllUsers()
        lateinit var user: UserResponse

        if(!existsUserAlready(listUsers, email, username)){
            api.insertNewUser(UserResponse("", username, email, password, null, null, "0", "0", emptyList()))
            return false
        }
        else return true
    }

    suspend fun updateUser(user: UserItem, password: String):String{
        val listUsers = api.getAllUsers().toMutableList()
        listUsers.remove(MainActivity.user.toDomain()) //Delete the user we are logged in as it will give user exists otherwise in case we dont change name and email

        if(existsUserAlready(listUsers, user.email,user.username)){
            return "exists"
        }else if(user.userPassword!=password){
            return "password"
        }else{
            api.updateUser(user.toPut())
            return "success"
        }

    }

    suspend fun updatePassword(user: UserItem, currentPassword: String): Boolean {
        if(currentPassword == MainActivity.user.userPassword){
            api.updateUser(user.toPut())
            MainActivity.user.userPassword = user.userPassword
            return true
        }
        return false
    }

    suspend fun getBookings(idUser: String): List<BookingItem> {
        return api.getBookings(idUser).map { it.toDomain(api.getUserById(it.idUser).toDomain(), getRoomById(it.idRoom)) }

    }

    private fun existsUserAlready(listUsers: List<UserResponse>, email: String, username: String): Boolean {

        val listUsersItem = listUsers.map { it.toDomain() }

        listUsersItem.forEach {
            if(it.email==email || it.username == username) return true
        }

        return false

    }

    fun isCorrectCredentials(listUsers: List<UserResponse>, username: String, password: String): UserItem?{
        val listUsersItem = listUsers.map { it.toDomain() }

        listUsersItem.forEach {
            if(it.username == username && it.userPassword == password) return it
        }
        return null
    }




}