package com.sara.travelagency.ui.view.bookings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sara.travelagency.R
import com.sara.travelagency.databinding.FragmentBookingsBinding
import com.sara.travelagency.domain.model.toDomain
import com.sara.travelagency.ui.view.MainActivity
import com.sara.travelagency.ui.view.bookings.recyclerview.BookingsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingsFragment : Fragment() {

    private val viewModel by viewModels<BookingsViewModel>()

    private var _binding: FragmentBookingsBinding?=null
    private val binding get() = _binding!!
    private lateinit var adapter: BookingsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initUI() {
        adapter = BookingsAdapter(navigateToWebsite = {navigateToWebsite(it)} )

        binding.rvBookings.setHasFixedSize(true)
        binding.rvBookings.layoutManager = GridLayoutManager(this.context, 1)
        binding.rvBookings.adapter = adapter

        viewModel.getMyBookings(MainActivity.user.idUser)
    }

    private fun initListeners() {
        binding.loading.isVisible = true
        viewModel.bookingsViewModel.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
            binding.swipe.isRefreshing=false
            binding.loading.isVisible = false
        })

        binding.swipe.setColorSchemeColors(resources.getColor(R.color.color_text), resources.getColor(R.color.background_text))

        binding.swipe.setOnRefreshListener {
            viewModel.getMyBookings(MainActivity.user.idUser)
        }
    }

    private fun navigateToWebsite(link: String) {
        val mainActivity = activity as MainActivity
        mainActivity.navigateToWebsite(link)
    }


}