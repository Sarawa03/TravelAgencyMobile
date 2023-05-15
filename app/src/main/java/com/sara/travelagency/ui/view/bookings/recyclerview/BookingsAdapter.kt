package com.sara.travelagency.ui.view.bookings.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sara.travelagency.R
import com.sara.travelagency.domain.model.BookingItem

import javax.inject.Inject

class BookingsAdapter @Inject constructor(
    private val navigateToWebsite: (String) -> Unit
): RecyclerView.Adapter<BookingsViewHolder>(){

    var bookingList: List<BookingItem> = emptyList()

    fun updateList(bookingList: List<BookingItem>){
        this.bookingList = bookingList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        return BookingsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.booking_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return bookingList.size
    }

    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        holder.bind(bookingList[position], navigateToWebsite)
    }


}