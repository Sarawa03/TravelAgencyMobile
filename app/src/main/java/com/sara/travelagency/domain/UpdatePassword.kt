package com.sara.travelagency.domain

import com.sara.travelagency.data.TravelAgencyRepository
import com.sara.travelagency.domain.model.UserItem
import javax.inject.Inject

class UpdatePassword @Inject constructor(private val repository: TravelAgencyRepository){

    suspend operator fun invoke(user: UserItem, currentPassword: String) = repository.updatePassword(user, currentPassword)

}