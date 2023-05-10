package com.sara.travelagency.domain.model

import com.sara.travelagency.data.model.HotelResponse
import com.sara.travelagency.data.model.RoomResponse

data class RoomItem (
    val idRoom: String,
    val price: String,
    val capacity: String,
    val booked: String,
    val hotel: HotelItem,
    )
data class HotelItem (
    val idHotel: String?,
    val logo: String,
    val hotelName: String,
    val address: String,
    val city: String,
    val stars: String,
    val website: String,
    val bookedTimes: String
    )

fun RoomResponse.toDomain(hotelItem: HotelItem)=RoomItem(idRoom, price, capacity, booked, hotelItem)
fun HotelResponse.toDomain()=HotelItem(idHotel, logo, hotelName, address, city, stars, website, bookedTimes)