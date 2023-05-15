package com.sara.travelagency.domain.model

import com.sara.travelagency.data.model.RoomResponse
import com.sara.travelagency.data.model.UserResponse
import java.io.Serializable


data class UserItem (
    val idUser: String,
    val username: String,
    val email: String,
    var userPassword: String,
    val phone: String?,
    val img: String?,
    val administrator: String,
    val bookedTimes: String,
    val bookedRooms: List<RoomResponse>
): Serializable

fun UserResponse.toDomain() = UserItem(idUser, username, email, userPassword, phone, img, administrator, bookedTimes, rooms)