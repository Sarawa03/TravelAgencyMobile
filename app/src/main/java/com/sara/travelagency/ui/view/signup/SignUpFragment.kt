package com.sara.travelagency.ui.view.signup

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.sara.travelagency.databinding.FragmentSignUpBinding
import com.sara.travelagency.domain.model.UserItem
import com.sara.travelagency.ui.view.AuthActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding?=null
    private val binding get() = _binding!!
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnSignUp.setOnClickListener {
            signUpViewModel.userSignUp(binding.etEmail.text.toString(), binding.etUsername.text.toString(), binding.etPassword.text.toString())
        }

        binding.btnLogin.setOnClickListener {
            navigateToLogIn()
        }

        signUpViewModel.isInsertedSignUpViewModel.observe(viewLifecycleOwner, Observer {
            if(it) showAlert()
            else {
                DynamicToast.makeSuccess(this.requireContext(), "User has been created", Toast.LENGTH_SHORT).show()
                navigateToLogIn()

            }
        })

        signUpViewModel.userLoginViewModel.observe(viewLifecycleOwner, Observer {
            if(it == null) showAlertLogin()
            else logIn(it)
        })
    }

    private fun showAlertLogin() {
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Error")
        builder.setMessage("Invalid Credentials: User/password doesn't not match")
        builder.setPositiveButton("Accept", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun navigateToLogIn() {
        val authActivity = activity as AuthActivity
        authActivity.navigateToLogIn()
    }
    private fun logIn(user: UserItem) {
        val authActivity = activity as AuthActivity
        authActivity.goHome(user)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun showAlert() {
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Error")
        builder.setMessage("Error: User already exists") //TODO comprobar email valido
        builder.setPositiveButton("Accept", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}