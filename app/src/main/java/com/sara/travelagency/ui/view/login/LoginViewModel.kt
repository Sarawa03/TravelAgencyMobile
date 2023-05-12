package com.sara.travelagency.ui.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.travelagency.domain.LogInUser
import com.sara.travelagency.domain.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUser: LogInUser
): ViewModel(){

    val userLoginViewModel = MutableLiveData<UserItem?>()

    fun userLogIn(username: String, password: String){
        viewModelScope.launch {
            val user = logInUser(username, password)
            userLoginViewModel.postValue(user)
        }

    }

}