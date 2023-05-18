package com.sara.travelagency.ui.view.details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.sara.travelagency.R
import com.sara.travelagency.databinding.ActivityDetailsBinding
import com.sara.travelagency.databinding.ActivityMainBinding
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.ui.view.MainActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.net.URI

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
    private var idRoom: String? = null
    private lateinit var url: String
    private lateinit var room: RoomItem

    private lateinit var dateCheckIn:String
    private lateinit var dateCheckOut:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idRoom = intent.getStringExtra("id")
        dateCheckIn = intent.getStringExtra("DATE_CHECK_IN")!!
        dateCheckOut = intent.getStringExtra("DATE_CHECK_OUT")!!

        viewModel.getRoomByIdViewModel(idRoom?: "error")

        viewModel.roomDetailsViewModel.observe(this, Observer {
            room = it
            loadUI(it)
        })

        initListeners()
    }



    private fun loadUI(roomItem: RoomItem) {
        binding.tvHotelName.text= roomItem.hotel.hotelName
        binding.tvAddress.text= roomItem.hotel.address
        binding.tvPrice.text= roomItem.price
        Picasso.get().load((MainActivity.baseUrl+ "hotels/logo/"+roomItem.hotel.idHotel)).into(binding.imgHotel)
        loadStars(roomItem.hotel.stars.toInt())
        url = roomItem.hotel.website
    }

    private fun initListeners() {
        binding.btnVisitWebsite.setOnClickListener {
            val link = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, link)
            startActivity(intent)
        }

        binding.btnBuy.setOnClickListener {
            viewModel.buyHotel(room, dateCheckIn, dateCheckOut)
            goToHome()
        }

        binding.btnCancel.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun goToHome() {

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("FRAGMENT", "Home")
        }
        startActivity(intent)
    }

    private fun loadStars(stars: Int) {
        if(stars > 0) binding.star1.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 1) binding.star2.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 2) binding.star3.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 3) binding.star4.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 4) binding.star5.setImageResource(R.drawable.ic_star_enabled)
    }
}

