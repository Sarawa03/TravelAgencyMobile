package com.sara.travelagency.ui.view.results

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sara.travelagency.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idRoomsList = intent.getStringArrayListExtra("LIST_ROOMS")
        Log.i("POTATO", "ResultActivity idRoomsList: $idRoomsList")

    }
}