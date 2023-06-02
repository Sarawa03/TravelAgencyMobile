package com.sara.travelagency.data

import android.util.Log
import com.sara.travelagency.data.model.BookingsResponse
import com.sara.travelagency.data.model.HotelResponse
import com.sara.travelagency.data.model.UserResponse
import com.sara.travelagency.data.model.toDomain
import com.sara.travelagency.data.model.toPut
import com.sara.travelagency.data.model.toResponse
import com.sara.travelagency.data.network.TravelAgencyService
import com.sara.travelagency.domain.model.BookingItem
import com.sara.travelagency.domain.model.HotelItem
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.domain.model.UserItem
import com.sara.travelagency.domain.model.toDomain
import com.sara.travelagency.ui.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class TravelAgencyRepository @Inject constructor(
    private val api: TravelAgencyService
){

    //Rooms
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

    suspend fun getHotelById(id: String): HotelItem{
        val hotelResponse = api.getHotelById(id)
        return hotelResponse.toDomain()
    }
    //Users

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
        Log.i("BUYHOTEL", "MAIN ACTIVITY USER ${MainActivity.user}")
        listUsers.remove(MainActivity.user.toDomain()) //Delete the user we are logged in as it will give user exists otherwise in case we dont change name and email

        if(existsUserAlready(listUsers, user.email,user.username)){
            return "exists"
        }else if(user.userPassword!=password){
            return "password"
        }else{
            MainActivity.user = user
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



    //Bookings
    suspend fun buyHotelRoom(room: RoomItem, dateCheckIn: String, dateCheckOut: String) {
        val user = MainActivity.user
        user.bookedTimes = (user.bookedTimes.toInt() +1).toString()

//TODO
        Log.i("BUYHOTEL", "User: $user ")
        Log.i("BUYHOTEL", "MainActivity.user: ${MainActivity.user} ")
        val roomItem = getRoomById(room.idRoom)
        Log.i("BUYHOTEL", "roomItem: $roomItem  ")

        val hotel = getHotelById(roomItem.hotel.idHotel!!).toResponse()
        hotel.bookedTimes = (hotel.bookedTimes.toInt() +1).toString()
        Log.i("BUYHOTEL", "hotel: $hotel ")
        api.updateHotel(hotel)
        Log.i("BUYHOTEL", "test")
        val nights = calculatesNights(dateCheckIn, dateCheckOut)
        val hotelRoom = BookingsResponse("", user.idUser, room.idRoom, dateCheckIn, dateCheckOut, calculatesPrice(nights, roomItem.price).toString()) //TODO TPRICE
        api.updateUser(user.toPut())
        api.buyHotelRoom(hotelRoom)
    }

    suspend fun getBookings(idUser: String): List<BookingItem> {
        return api.getBookings(idUser).map { it.toDomain(api.getUserById(it.idUser).toDomain(), getRoomById(it.idRoom)) }

    }

    //Boolean functions
    private fun existsUserAlready(listUsers: List<UserResponse>, email: String, username: String): Boolean {

        val listUsersItem = listUsers.map { it.toDomain() }

        listUsersItem.forEach {
            if(it.email==email || it.username == username) return true
        }

        return false

    }

    fun calculatesNights(dateCheckInStr: String, dateCheckOutStr: String): Long {
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        val startDate = LocalDateTime.parse(dateCheckInStr, formatter)
        val endDate = LocalDateTime.parse(dateCheckOutStr, formatter)
        val days = ChronoUnit.DAYS.between(startDate, endDate)


        return days
    }

    fun calculatesPrice(nights:Long, price: String): Double{
        val pricePerNight = price.toDouble()
        return (nights*pricePerNight)
    }

    fun isCorrectCredentials(listUsers: List<UserResponse>, username: String, password: String): UserItem?{
        val listUsersItem = listUsers.map { it.toDomain() }

        listUsersItem.forEach {
            if(it.username == username && it.userPassword == password) return it
        }
        return null
    }




}