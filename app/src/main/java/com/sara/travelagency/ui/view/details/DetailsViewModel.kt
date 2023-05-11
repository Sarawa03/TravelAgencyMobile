package com.sara.travelagency.ui.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.travelagency.domain.GetRoomById
import com.sara.travelagency.domain.model.RoomItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getRoomById: GetRoomById
): ViewModel(){

    val roomDetailsViewModel = MutableLiveData<RoomItem>()

    fun getRoomByIdViewModel(id: String){
        if(id!="error"){
            viewModelScope.launch {
                val result = getRoomById(id)
                roomDetailsViewModel.postValue(result)
            }
        }

    }

}