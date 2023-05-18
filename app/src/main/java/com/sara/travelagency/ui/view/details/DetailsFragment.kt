package com.sara.travelagency.ui.view.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sara.travelagency.R
import com.sara.travelagency.databinding.FragmentDetailsBinding
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.ui.view.MainActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

private const val ID_ROOM = "id"
private const val DATE_CHECK_IN = "DATE_CHECK_IN"
private const val DATE_CHECK_OUT = "DATE_CHECK_OUT"

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailsViewModel>()
    private var idRoom: String? = null
    private lateinit var url: String
    private lateinit var room: RoomItem

    private lateinit var dateCheckIn:String
    private lateinit var dateCheckOut:String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRoomByIdViewModel(idRoom?: "error")

        viewModel.roomDetailsViewModel.observe(viewLifecycleOwner, Observer {
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
            onBackPressed()
        }
    }

    private fun onBackPressed(){
        val mainActivity = activity as MainActivity
        mainActivity.pressBack()
    }

    private fun goToHome() {
        val mainActivity = activity as MainActivity
        mainActivity.goToHome()

    }


    private fun loadStars(stars: Int) {
        if(stars > 0) binding.star1.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 1) binding.star2.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 2) binding.star3.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 3) binding.star4.setImageResource(R.drawable.ic_star_enabled)
        if(stars > 4) binding.star5.setImageResource(R.drawable.ic_star_enabled)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idRoom = it.getString(ID_ROOM)
            dateCheckIn = it.getString(DATE_CHECK_IN)!!
            dateCheckOut = it.getString(DATE_CHECK_OUT)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(idRoom: String, dateCheckIn: String, dateCheckOut: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_ROOM, idRoom)
                    putString(DATE_CHECK_IN, dateCheckIn)
                    putString(DATE_CHECK_OUT, dateCheckOut)
                }
            }
    }
}