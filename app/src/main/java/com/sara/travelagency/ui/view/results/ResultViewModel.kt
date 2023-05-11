package com.sara.travelagency.ui.view.results

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.travelagency.domain.GetRoomById
import com.sara.travelagency.domain.model.RoomItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val getRoomById: GetRoomById
): ViewModel(){
    val resultViewModel = MutableLiveData<List<RoomItem>>()

    fun lookUpRooms(idRoomsList: List<String>) {

        viewModelScope.launch {
            val listRooms: MutableList<RoomItem> = mutableListOf()
            idRoomsList.forEach {
                listRooms.add(getRoomById(it))
            }
            resultViewModel.postValue(listRooms)
        }
    }

}