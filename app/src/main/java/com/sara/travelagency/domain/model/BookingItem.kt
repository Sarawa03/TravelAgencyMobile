package com.sara.travelagency.domain.model

import com.google.gson.annotations.SerializedName
import com.sara.travelagency.data.model.BookingsResponse

data class BookingItem (
    val idBooking: String,
    val idUser: String,
    val idRoom: String,
    val initialDate: String,
    val endDate: String
)

fun BookingsResponse.toDomain() = BookingItem(idbooking, idUser, idRoom, initialDate, endDate)