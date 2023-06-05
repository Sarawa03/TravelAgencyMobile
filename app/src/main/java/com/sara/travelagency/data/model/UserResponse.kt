package com.sara.travelagency.data.model

import com.google.gson.annotations.SerializedName
import com.sara.travelagency.domain.model.UserItem

data class UserResponse (
    @SerializedName("idUser") val idUser: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("userPassword") val userPassword: String,
    @SerializedName("phone") val phone: String?,
    @SerializedName("administrator") val administrator: String,
    @SerializedName("bookedTimes") val bookedTimes: String,
    @SerializedName("rooms") val rooms: List<RoomResponse>
)

fun UserItem.toDomain() = UserResponse(idUser, username, email, userPassword, phone, administrator, bookedTimes, bookedRooms)
