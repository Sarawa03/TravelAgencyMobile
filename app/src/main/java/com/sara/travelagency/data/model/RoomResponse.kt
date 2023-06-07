package com.sara.travelagency.data.model

import com.google.gson.annotations.SerializedName
import com.sara.travelagency.domain.model.RoomItem
import java.io.Serializable

data class RoomResponse (
    @SerializedName("idroom") val idRoom: String,
    @SerializedName("price") val price: String,
    @SerializedName("capacity") val capacity: String,
    @SerializedName("hotel") val hotel: String
    ): Serializable

fun RoomItem.toDomain() = RoomResponse(idRoom, price, capacity, hotel.idHotel!!)