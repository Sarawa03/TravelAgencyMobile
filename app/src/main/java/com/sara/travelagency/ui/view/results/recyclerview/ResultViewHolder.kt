package com.sara.travelagency.ui.view.results.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sara.travelagency.R
import com.sara.travelagency.databinding.ResultsItemBinding
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.ui.view.MainActivity
import com.squareup.picasso.Picasso
import java.lang.StringBuilder
import java.text.DecimalFormat

class ResultViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val binding = ResultsItemBinding.bind(view)


    fun bind(roomItem: RoomItem, onItemSelected: (String) -> Unit ){
        binding.hotelName.text = roomItem.hotel.hotelName
        binding.hotelAddress.text = roomItem.hotel.address



        val sb = StringBuilder()
        sb.append(roomItem.price)
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

}