package com.sara.travelagency.data.model

import com.google.gson.annotations.SerializedName

data class BookingsResponse (
    @SerializedName("idbooking") val idbooking: String,
    @SerializedName("iduser") val idUser: String,
    @SerializedName("idroom") val idRoom: String,
    @SerializedName("initialDate") val initialDate: String,
    @SerializedName("endDate") val endDate: String,
    @SerializedName("totalPayed") val totalPayed: String
    )