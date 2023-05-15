package com.sara.travelagency.ui.view.bookings.recyclerview

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sara.travelagency.R
import com.sara.travelagency.databinding.BookingItemBinding
import com.sara.travelagency.domain.model.BookingItem
import com.sara.travelagency.ui.view.MainActivity
import com.squareup.picasso.Picasso

class BookingsViewHolder (view:View): RecyclerView.ViewHolder(view) {

    private val binding = BookingItemBinding.bind(view)

    fun bind(bookingItem: BookingItem, navigateToWebsite: (String) -> Unit){
        binding.hotelName.text = bookingItem.room.hotel.hotelName
        binding.hotelPrice.text = bookingItem.room.price
        binding.hotelAddress.text = bookingItem.room.hotel.address

        Picasso.get().load((MainActivity.baseUrl+ "hotels/logo/"+bookingItem.room.hotel.idHotel)).into(binding.hotelLogo)

        loadStars(bookingItem.room.hotel.stars.toInt())

        binding.btnVisitWebsite.setOnClickListener { navigateToWebsite(bookingItem.room.hotel.website) }
    }

    private fun loadStars(stars: Int) {
        if(stars > 0) binding.star1.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 1) binding.star2.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 2) binding.star3.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 3) binding.star4.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 4) binding.star5.setImageResource(R.drawable.ic_star_enabled)

    }
}
