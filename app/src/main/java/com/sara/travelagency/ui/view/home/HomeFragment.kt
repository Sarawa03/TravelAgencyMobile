package com.sara.travelagency.ui.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import com.sara.travelagency.R
import com.sara.travelagency.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding?=null
    private val binding get()=_binding!!
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var adapterItems: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initListeners()
    }

    private fun initUI() {
        autoCompleteTextView = binding.tvAutoComplete
        var adapterItems = ArrayAdapter(this.requireContext(), R.layout.country_dropdown_list, viewModel.allCountries())
        autoCompleteTextView.setAdapter(adapterItems)

    }

    private fun initListeners() {

        binding.btnLookUp.setOnClickListener {
            viewModel.lookUpRoom(binding.tvAutoComplete.text.toString(), binding.etPeople.text.toString(), binding.etBudget.text.toString())
//            Log.i("POTATO", binding.tvAutoComplete.text.toString())
//            Log.i("POTATO", binding.etPeople.text.toString())
//            Log.i("POTATO", binding.etBudget.text.toString())
        }
//        autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
//            val itemSelected = adapterView.getItemAtPosition(i)
//
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


}