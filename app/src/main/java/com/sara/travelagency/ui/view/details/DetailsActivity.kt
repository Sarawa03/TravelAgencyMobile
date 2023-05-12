package com.sara.travelagency.ui.view.details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sara.travelagency.R
import com.sara.travelagency.databinding.ActivityDetailsBinding
import com.sara.travelagency.databinding.ActivityMainBinding
import com.sara.travelagency.domain.model.RoomItem
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.net.URI

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
    private var idRoom: String? = null
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idRoom = intent.getStringExtra("id")

        viewModel.getRoomByIdViewModel(idRoom?: "error")

        viewModel.roomDetailsViewModel.observe(this, Observer {
            loadUI(it)
        })

        initListeners()
    }



    private fun loadUI(roomItem: RoomItem) {
        binding.tvHotelName.text= roomItem.hotel.hotelName
        binding.tvAddress.text= roomItem.hotel.address
        binding.tvPrice.text= roomItem.price
        //Picasso.get().load(roomItem.hotel.logo).into(binding.imgHotel)
        Picasso.get().load(("http://169.254.154.183:8080/travelagency/hotels/logo/"+roomItem.hotel.idHotel)).into(binding.imgHotel)
        loadStars(roomItem.hotel.stars.toInt())
        url = roomItem.hotel.website
    }

    private fun initListeners() {
        binding.btnVisitWebsite.setOnClickListener {
            val link = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, link)
            startActivity(intent)
        }

        binding.btnCancel.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun loadStars(stars: Int) {
        if(stars > 0) binding.star1.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 1) binding.star2.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 2) binding.star3.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 3) binding.star4.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 4) binding.star5.setImageResource(R.drawable.ic_star_enabled)
    }
}

