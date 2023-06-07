package com.sara.travelagency.ui.view.signup


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.travelagency.domain.LogInUser
import com.sara.travelagency.domain.SignUpUser
import com.sara.travelagency.domain.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUser: SignUpUser,

): ViewModel(){

    val isInsertedSignUpViewModel = MutableLiveData<Boolean>()
    val userLoginViewModel = MutableLiveData<UserItem?>()

    fun userSignUp(email: String, username: String, password: String){
        viewModelScope.launch {
            val isInserted = signUpUser(email, username, password)

            isInsertedSignUpViewModel.postValue(isInserted)
        }
    }


}