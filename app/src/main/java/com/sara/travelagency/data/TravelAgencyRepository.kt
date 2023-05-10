package com.sara.travelagency.data

import android.util.Log
import com.sara.travelagency.data.network.TravelAgencyService
import com.sara.travelagency.domain.model.BookingItem
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.domain.model.toDomain
import javax.inject.Inject

class TravelAgencyRepository @Inject constructor(
    private val api: TravelAgencyService
){
    suspend fun lookUpRoom(city: String, capacity: String, price: String, dateCheckIn: String, dateCheckOut: String): List<RoomItem> {
        //TODO, MAP LIST
        Log.i("PATATA", "Room:" +api.getHotelById("3"))
        Log.i("PATATA", "BOOKING:" +api.hotelsInRange(dateCheckIn, dateCheckOut))

        val bookings = api.hotelsInRange(dateCheckIn, dateCheckOut).map {it.toDomain()}
        
        val rooms = api.lookUpRoom(city, capacity, price).map {it.toDomain(api.getHotelById(it.hotel).toDomain())}

        return getUnbookedRooms(bookings, rooms)
    }

    private fun getUnbookedRooms(bookings: List<BookingItem>, rooms: List<RoomItem>): List<RoomItem> {
//        val unbookedRooms = mutableSetOf<RoomItem>()
        val unbookedRooms = rooms.toMutableSet()
        Log.i("ROOMS", rooms.toString())
        Log.i("BOOKINGS", bookings.toString())
        rooms.map { room ->
            bookings.map { booking ->
                Log.i("POTATO", "${booking.idRoom} != ${room.idRoom} = ${booking.idRoom!=room.idRoom}")
                if(booking.idRoom==room.idRoom){//Esta ocupada
                    unbookedRooms.remove(room)
                    //unbookedRooms.add(room)
                    Log.i("POTATO", "Unbooked room removed: $room")

                }else{
                    Log.i("POTATO", "Unbooked room not removed: $room")
                }
            }
        }
        return unbookedRooms.toList()
    }


}