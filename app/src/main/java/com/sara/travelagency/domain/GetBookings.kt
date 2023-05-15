package com.sara.travelagency.domain

import com.sara.travelagency.data.TravelAgencyRepository
import javax.inject.Inject

class GetBookings @Inject constructor(private val repository: TravelAgencyRepository){

    suspend operator fun invoke(idUser: String) = repository.getBookings(idUser)
}