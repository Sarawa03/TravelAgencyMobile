package com.sara.travelagency.ui.view.resetpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.travelagency.domain.UpdatePassword
import com.sara.travelagency.domain.UpdateUser
import com.sara.travelagency.domain.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val updatePassword: UpdatePassword
): ViewModel(){

    val resetPasswordViewModel = MutableLiveData<Boolean>()

    fun userPassword(user: UserItem, currentPassword: String){
        viewModelScope.launch {
            val userUpdated = updatePassword(user, currentPassword)
            resetPasswordViewModel.postValue(userUpdated)
        }
    }

}