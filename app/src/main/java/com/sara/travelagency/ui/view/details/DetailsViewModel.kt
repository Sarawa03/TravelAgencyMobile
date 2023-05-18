package com.sara.travelagency.ui.view.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.sara.travelagency.domain.BuyHotelRoom
import com.sara.travelagency.domain.GetRoomById
import com.sara.travelagency.domain.model.RoomItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getRoomById: GetRoomById,
    private val buyHotelRoom: BuyHotelRoom
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

    fun buyHotel(room: RoomItem, dateCheckIn: String, dateCheckOut: String) {
        viewModelScope.launch {
            Log.i("SARAWA_BUY", "room $room, dateCheckIn $dateCheckIn, dateCheckOut $dateCheckOut")
            buyHotelRoom(room, dateCheckIn, dateCheckOut)
        }
    }

}