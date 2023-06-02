package com.sara.travelagency.data.model

import com.google.gson.annotations.SerializedName
import com.sara.travelagency.domain.model.HotelItem

data class HotelResponse (
    @SerializedName("idHotel") val idHotel: String?,
    @SerializedName("logo") val logo: String?,
    @SerializedName("hotelName") val hotelName: String,
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("stars") val stars: String,
    @SerializedName("website") val website: String,
    @SerializedName("bookedTimes") var bookedTimes: String
    )

fun HotelItem.toResponse() = HotelResponse(idHotel, logo, hotelName, address, city, stars, website, bookedTimes)