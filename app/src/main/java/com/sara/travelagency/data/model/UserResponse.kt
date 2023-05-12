package com.sara.travelagency.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("idUser") val idUser: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("userPassword") val userPassword: String,
    @SerializedName("phone") val phone: String?,
    @SerializedName("img") val img: String?,
    @SerializedName("administrator") val administrator: String,
    @SerializedName("bookedTimes") val bookedTimes: String,
    @SerializedName("rooms") val bookedRooms: List<RoomResponse>
)