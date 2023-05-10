package com.sara.travelagency.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sara.travelagency.R
import com.sara.travelagency.databinding.ActivityMainBinding
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.ui.view.results.ResultActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)
    }

    fun navigateToResults(roomsList: List<RoomItem>) {
        val idRoomsList = mutableListOf<String>()
        roomsList.forEach {
            idRoomsList.add(it.idRoom)
        }
        val intent = Intent(this, ResultActivity::class.java).apply {

            putStringArrayListExtra("LIST_ROOMS", ArrayList(idRoomsList)) }
        startActivity(intent)

    }


}
