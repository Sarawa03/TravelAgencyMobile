package com.sara.travelagency.ui.view.results

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sara.travelagency.R
import com.sara.travelagency.databinding.ActivityResultBinding
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.ui.view.details.DetailsActivity
import com.sara.travelagency.ui.view.results.recyclerview.ResultAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private val viewModel by viewModels<ResultViewModel>()
    private var idRoomsList: ArrayList<String>?= arrayListOf()
    private lateinit var adapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        initUI()


    }

    private fun initUI() {
        adapter = ResultAdapter(onItemSelected = {navigateToDetail(it)})
        idRoomsList = intent.getStringArrayListExtra("LIST_ROOMS")
        Log.i("POTATO", "ResultActivity idRoomsList: $idRoomsList")
        binding.rvHotelResults.setHasFixedSize(true)
        binding.rvHotelResults.layoutManager = GridLayoutManager(this, 1)
        binding.rvHotelResults.adapter = adapter

        val nonNullIdRoomList: List<String>
        if(idRoomsList==null) nonNullIdRoomList = emptyList()
        else nonNullIdRoomList = idRoomsList!!.toList()
        viewModel.lookUpRooms(nonNullIdRoomList)
    }

    private fun initListeners() {

        binding.loading.isVisible = true

        viewModel.resultViewModel.observe(this, Observer {
            adapter.updateList(it)
            binding.swipe.isRefreshing = false
            binding.loading.isVisible = false
        })

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.swipe.setColorSchemeColors(resources.getColor(R.color.color_text), resources.getColor(R.color.background_text))
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            val nonNullIdRoomList: List<String>
            if(idRoomsList==null) nonNullIdRoomList = emptyList()
            else nonNullIdRoomList = idRoomsList!!.toList()
            viewModel.lookUpRooms(nonNullIdRoomList)
        }
    }
    private fun navigateToDetail(id: String){
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("id", id)
        }
        startActivity(intent)
    }

}