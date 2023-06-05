package com.sara.travelagency.data.model

import com.google.gson.annotations.SerializedName
import com.sara.travelagency.domain.model.UserItem

data class UserPut (
    @SerializedName("idUser") val idUser: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("userPassword") val userPassword: String,
    @SerializedName("phone") val phone: String?,
    @SerializedName("administrator") val administrator: String,
    @SerializedName("bookedTimes") val bookedTimes: String
)

fun UserItem.toPut() = UserPut(idUser, username, email, userPassword, phone, administrator, bookedTimes)