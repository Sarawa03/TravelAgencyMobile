
package com.sara.travelagency.ui.view.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.sara.travelagency.R
import com.sara.travelagency.databinding.FragmentAccountBinding
import com.sara.travelagency.databinding.FragmentLoginBinding
import com.sara.travelagency.domain.model.UserItem
import com.sara.travelagency.ui.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private val viewModel by viewModels<AccountViewModel>()

    private var _binding: FragmentAccountBinding?=null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initListeners()
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

    private fun initListeners() {
        val currentUser = MainActivity.user
        binding.btnSave.setOnClickListener {
            viewModel.userUpdate(UserItem(currentUser.idUser, binding.etUsername.text.toString(), binding.etEmail.text.toString(), binding.etPassword.text.toString(), binding.etPhone.text.toString(), currentUser.img, currentUser.administrator, currentUser.bookedTimes, currentUser.bookedRooms), currentUser.userPassword)
        }

        binding.btnLogOut.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.navigateToAuth()
        }

        binding.tvForgotPassword.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.navigateToResetPassword()
        }

        viewModel.userAccountViewModel.observe(viewLifecycleOwner, Observer {

            Log.i("ACCOUNT", it)
            if(it!="success")DynamicToast.makeError(this.requireContext(), it, Toast.LENGTH_SHORT).show()
            else{
                DynamicToast.makeSuccess(this.requireContext(), "User has been succesfully updated", Toast.LENGTH_SHORT).show()

            }
        })

    }
}