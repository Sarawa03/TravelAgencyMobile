package com.sara.travelagency.ui.view.results.recyclerview

import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.sara.travelagency.R
import com.sara.travelagency.databinding.ResultsItemBinding
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.ui.view.MainActivity
import com.squareup.picasso.Picasso
import java.lang.StringBuilder
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class ResultViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val binding = ResultsItemBinding.bind(view)


    fun bind(roomItem: RoomItem, dateCheckIn: String, dateCheckOut: String, onItemSelected: (String) -> Unit ){
        binding.hotelName.text = roomItem.hotel.hotelName
        binding.hotelAddress.text = roomItem.hotel.address



        val sb = StringBuilder()
        val nights = calculatesNights(dateCheckIn, dateCheckOut)
        sb.append(calculatesPrice(nights, roomItem.price).toString())
        sb.append(" €")
        binding.hotelPrice.text = sb.toString()

        Picasso.get().load((MainActivity.baseUrl+ "hotels/logo/"+roomItem.hotel.idHotel)).into(binding.hotelLogo)
        loadStars(roomItem.hotel.stars.toInt())

        binding.root.setOnClickListener {onItemSelected(roomItem.idRoom)}
    }

    private fun loadStars(stars: Int) {
        if(stars > 0) binding.star1.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 1) binding.star2.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 2) binding.star3.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 3) binding.star4.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 4) binding.star5.setImageResource(R.drawable.ic_star_enabled)

    }
    //@RequiresApi(Build.VERSION_CODES.O)
    fun calculatesNights(dateCheckInStr: String, dateCheckOutStr: String): Long {
        Log.i("NIGHTS", "dateCheckInStr $dateCheckInStr")
        Log.i("NIGHTS", "dateCheckOutStr $dateCheckOutStr")

        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        val startDate = LocalDateTime.parse(dateCheckInStr, formatter)
        val endDate = LocalDateTime.parse(dateCheckOutStr, formatter)


        Log.i("NIGHTS", "startDate $startDate")
        Log.i("NIGHTS", "endDate $endDate")

        val days = ChronoUnit.DAYS.between(startDate, endDate)
        Log.i("NIGHTS", "days $days")

        return days
    }

    fun calculatesPrice(nights:Long, price: String): Double{
        val pricePerNight = price.toDouble()
        return (nights*pricePerNight.toDouble())
    }

}