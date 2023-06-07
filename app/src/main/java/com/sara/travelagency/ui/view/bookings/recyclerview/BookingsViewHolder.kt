package com.sara.travelagency.ui.view.bookings.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sara.travelagency.R
import com.sara.travelagency.databinding.BookingItemBinding
import com.sara.travelagency.domain.model.BookingItem
import com.sara.travelagency.ui.view.MainActivity
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.text.StringBuilder

class BookingsViewHolder (view:View): RecyclerView.ViewHolder(view) {

    private val binding = BookingItemBinding.bind(view)

    fun bind(bookingItem: BookingItem, navigateToWebsite: (String) -> Unit){
        binding.hotelName.text = bookingItem.room.hotel.hotelName
        binding.hotelAddress.text = bookingItem.room.hotel.address
        val sbPrice = StringBuilder()
        sbPrice.append(bookingItem.totalPayed)
        sbPrice.append(" €")

        binding.hotelPrice.text =  sbPrice.toString()
        val sb = StringBuilder()
        sb.append(bookingItem.room.capacity)
        sb.append(" Person/s | ")
        sb.append(parseDate(bookingItem.initialDate))
        sb.append(" - ")
        sb.append(parseDate(bookingItem.endDate))

        binding.tvBookingDetails.text = sb.toString()

        Picasso.get().load((MainActivity.baseUrl+ "hotels/logo/"+bookingItem.room.hotel.idHotel)).into(binding.hotelLogo)

        loadStars(bookingItem.room.hotel.stars.toInt())

        binding.btnVisitWebsite.setOnClickListener { navigateToWebsite(bookingItem.room.hotel.website) }
    }

    fun parseDate(dateString: String): String {
        val sourceFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val targetFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        val dateTime = LocalDateTime.parse(dateString, sourceFormat)
        return dateTime.format(targetFormat)
    }

    private fun loadStars(stars: Int) {
        if(stars > 0) binding.star1.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 1) binding.star2.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 2) binding.star3.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 3) binding.star4.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 4) binding.star5.setImageResource(R.drawable.ic_star_enabled)

    }
}
