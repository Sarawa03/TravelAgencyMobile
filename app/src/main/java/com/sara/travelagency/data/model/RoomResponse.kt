package com.sara.travelagency.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RoomResponse (
    @SerializedName("idroom") val idRoom: String,
    @SerializedName("price") val price: String,
    @SerializedName("capacity") val capacity: String,
    @SerializedName("booked") val booked: String,
    @SerializedName("hotel") val hotel: String
    ): Serializable