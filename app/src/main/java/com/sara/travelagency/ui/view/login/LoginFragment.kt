package com.sara.travelagency.ui.view.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sara.travelagency.R
import com.sara.travelagency.databinding.FragmentLoginBinding
import com.sara.travelagency.domain.model.UserItem
import com.sara.travelagency.ui.view.AuthActivity
import com.sara.travelagency.ui.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            loginViewModel.userLogIn(binding.etUsername.text.toString(), binding.etPassword.text.toString())
        }

        binding.btnSignUp.setOnClickListener {
            navigateToSignUp()
        }

        loginViewModel.userLoginViewModel.observe(viewLifecycleOwner, Observer {
            if(it == null) showAlert()
            else logIn(it)
        })
    }

    private fun navigateToSignUp() {
        val authActivity = activity as AuthActivity
        authActivity.navigateToSignUp()
    }

    private fun logIn(user: UserItem) {
        val authActivity = activity as AuthActivity
        authActivity.goHome(user)
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Error")
        builder.setMessage("Invalid Credentials: User/password doesn't not match")
        builder.setPositiveButton("Accept", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



}