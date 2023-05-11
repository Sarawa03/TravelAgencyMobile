package com.sara.travelagency.domain

import com.sara.travelagency.data.TravelAgencyRepository
import javax.inject.Inject

class GetRoomById @Inject constructor(private val repository: TravelAgencyRepository){

    suspend operator fun invoke(id: String) = repository.getRoomById(id)
}