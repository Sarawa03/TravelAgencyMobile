package com.sara.travelagency.data.model

import com.google.gson.annotations.SerializedName

data class HotelResponse (
    @SerializedName("idHotel") val idHotel: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("hotelName") val hotelName: String,
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("stars") val stars: String,
    @SerializedName("website") val website: String,
    @SerializedName("bookedTimes") val bookedTimes: String
    )