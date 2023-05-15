package com.sara.travelagency.domain

import com.sara.travelagency.data.TravelAgencyRepository
import com.sara.travelagency.domain.model.UserItem
import javax.inject.Inject

class UpdateUser @Inject constructor(private val repository: TravelAgencyRepository){

    suspend operator fun invoke(user:UserItem, password: String) = repository.updateUser(user, password)
}