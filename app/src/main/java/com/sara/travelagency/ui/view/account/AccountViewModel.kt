package com.sara.travelagency.ui.view.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.travelagency.domain.UpdateUser
import com.sara.travelagency.domain.model.UserItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val updateUser: UpdateUser
): ViewModel(){

    val userAccountViewModel = MutableLiveData<String>()

    fun userUpdate(user: UserItem, password: String){
        Log.i("ACCOUNT", "User: $user , pass: $password")
        viewModelScope.launch {
            val userUpdated = updateUser(user, password)
            userAccountViewModel.postValue(userUpdated)
        }
    }

}