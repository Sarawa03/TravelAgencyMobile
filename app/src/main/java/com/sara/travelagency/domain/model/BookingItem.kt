package com.sara.travelagency.domain.model

import com.google.gson.annotations.SerializedName
import com.sara.travelagency.data.model.BookingsResponse

data class BookingItem (
    val idBooking: String,
    val user: UserItem,
    val room: RoomItem,
    val initialDate: String,
    val endDate: String
)

fun BookingsResponse.toDomain(user: UserItem, room: RoomItem) = BookingItem(idbooking, user, room, initialDate, endDate)