package com.sara.travelagency.ui.view.bookings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sara.travelagency.domain.GetBookings
import com.sara.travelagency.domain.model.BookingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingsViewModel @Inject constructor(
    private val getBookings: GetBookings
): ViewModel(){

    val bookingsViewModel = MutableLiveData<List<BookingItem>>()

    fun getMyBookings(idUser: String){
        viewModelScope.launch {
            //val listBookings: MutableList<BookingItem> = mutableListOf()
            val result = getBookings(idUser)
            bookingsViewModel.postValue(result)
        }
    }
}