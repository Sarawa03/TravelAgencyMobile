package com.sara.travelagency.ui.view.results

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
import com.sara.travelagency.databinding.FragmentDetailsBinding
import com.sara.travelagency.databinding.FragmentResultBinding
import com.sara.travelagency.ui.view.MainActivity
import com.sara.travelagency.ui.view.results.recyclerview.ResultAdapter
import dagger.hilt.android.AndroidEntryPoint


private const val LIST_ROOMS = "LIST_ROOMS"
private const val DATE_CHECK_IN = "DATE_CHECK_IN"
private const val DATE_CHECK_OUT = "DATE_CHECK_OUT"

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ResultViewModel>()
    private lateinit var adapter: ResultAdapter

    private var idRoomsList: ArrayList<String>?= arrayListOf()
    private var dateCheckIn: String? = null
    private var dateCheckOut: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initUI()
    }

    private fun initUI() {
        adapter = ResultAdapter(onItemSelected = {navigateToDetail(it)}, dateCheckIn!!, dateCheckOut!!)

        binding.rvHotelResults.setHasFixedSize(true)
        binding.rvHotelResults.layoutManager = GridLayoutManager(this.context, 1)
        binding.rvHotelResults.adapter = adapter

        val nonNullIdRoomList: List<String>
        if(idRoomsList==null) nonNullIdRoomList = emptyList()
        else nonNullIdRoomList = idRoomsList!!.toList()
        viewModel.lookUpRooms(nonNullIdRoomList)
    }

    private fun initListeners() {

        binding.loading.isVisible = true

        viewModel.resultViewModel.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
            binding.swipe.isRefreshing = false
            binding.loading.isVisible = false
        })

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.swipe.setColorSchemeColors(resources.getColor(R.color.color_text), resources.getColor(R.color.background_text))
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            val nonNullIdRoomList: List<String>
            if(idRoomsList==null) nonNullIdRoomList = emptyList()
            else nonNullIdRoomList = idRoomsList!!.toList()
            viewModel.lookUpRooms(nonNullIdRoomList)
        }
    }

    private fun navigateToDetail(id: String){

        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("DATE_CHECK_IN", dateCheckIn)
        bundle.putString("DATE_CHECK_OUT", dateCheckOut)
        val mainActivity = activity as MainActivity
        mainActivity.navigateToDetail(bundle)

    }

    private fun onBackPressed(){
        val mainActivity = activity as MainActivity
        mainActivity.pressBack()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idRoomsList = it.getStringArrayList(LIST_ROOMS)
            dateCheckIn = it.getString(DATE_CHECK_IN)
            dateCheckOut = it.getString(DATE_CHECK_OUT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(idRoomsList: ArrayList<String>, dateCheckIn: String, dateCheckOut: String) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(LIST_ROOMS, idRoomsList)
                    putString(DATE_CHECK_IN, dateCheckIn)
                    putString(DATE_CHECK_OUT, dateCheckOut)
                }
            }
    }
}