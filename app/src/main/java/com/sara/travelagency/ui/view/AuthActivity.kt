package com.sara.travelagency.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sara.travelagency.R
import com.sara.travelagency.domain.model.UserItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val authNavHostFragment = supportFragmentManager.findFragmentById(R.id.auth_nav_host_fragment) as NavHostFragment
        navController = authNavHostFragment.navController

    }

    fun goHome(user: UserItem) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("USER_ITEM", user)
        }
        startActivity(intent)
    }

    fun navigateToSignUp() {
        navController.navigate(R.id.signUpFragment)
    }

    fun navigateToLogIn() {
        navController.navigate(R.id.loginFragment)
    }
}