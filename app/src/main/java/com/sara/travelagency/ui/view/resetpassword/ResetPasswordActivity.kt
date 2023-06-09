package com.sara.travelagency.ui.view.resetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.sara.travelagency.R
import com.sara.travelagency.databinding.ActivityResetPasswordBinding
import com.sara.travelagency.domain.model.UserItem
import com.sara.travelagency.ui.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private  val viewModel by viewModels<ResetPasswordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("RESETPASS","MainActivity: ${MainActivity.user}")
        initListeners()
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {
            val u = MainActivity.user
            Log.i("RESETPASS","MainActivity: ${MainActivity.user}")
            viewModel.userPassword(UserItem(u.idUser, u.username, u.email, binding.etNewPassword.text.toString(), u.phone, u.administrator, u.bookedTimes, u.bookedRooms), binding.etPassword.text.toString())
        }

        binding.btnCancel.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        viewModel.resetPasswordViewModel.observe(this, Observer {
            if(it)DynamicToast.makeSuccess(this, "Password changed succesfully", Toast.LENGTH_SHORT).show()
            else DynamicToast.makeError(this, "Password error", Toast.LENGTH_SHORT).show()
        })
    }
}