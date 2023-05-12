package com.sara.travelagency.domain

import com.sara.travelagency.data.TravelAgencyRepository
import javax.inject.Inject

class LogInUser @Inject constructor(private val repository: TravelAgencyRepository){

    suspend operator fun invoke(username: String, password: String) = repository.logInUser(username, password)
}