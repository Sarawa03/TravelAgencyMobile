package com.sara.travelagency.ui.view.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sara.travelagency.R
import com.sara.travelagency.databinding.FragmentLoginBinding
import com.sara.travelagency.databinding.FragmentSignUpBinding
import com.sara.travelagency.ui.view.AuthActivity


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding?=null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            navigateToLogIn()
        }
    }

    private fun navigateToLogIn() {
        val authActivity = activity as AuthActivity
        authActivity.navigateToLogIn()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }


}