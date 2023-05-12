
package com.sara.travelagency.ui.view.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sara.travelagency.R
import com.sara.travelagency.databinding.FragmentAccountBinding
import com.sara.travelagency.databinding.FragmentLoginBinding
import com.sara.travelagency.ui.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding?=null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun initUI() {
        binding.etUsername.setText(MainActivity.user.username)
        binding.etEmail.setText(MainActivity.user.email)
        if(MainActivity.user.phone!=null)binding.etPhone.setText(MainActivity.user.phone)
    }

}