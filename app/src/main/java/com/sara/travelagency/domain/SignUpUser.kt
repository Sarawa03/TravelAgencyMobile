package com.sara.travelagency.domain

import com.sara.travelagency.data.TravelAgencyRepository
import javax.inject.Inject

class SignUpUser @Inject constructor(private val repository: TravelAgencyRepository){

    suspend operator fun invoke(email: String, username: String, password: String) = repository.signUp(email, username, password)
}