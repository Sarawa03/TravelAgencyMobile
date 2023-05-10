package com.sara.travelagency.ui.view.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import com.sara.travelagency.R
import com.sara.travelagency.databinding.FragmentHomeBinding
import com.sara.travelagency.domain.model.RoomItem
import com.sara.travelagency.ui.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding?=null
    private val binding get()=_binding!!
    private lateinit var autoCompleteTextView: AutoCompleteTextView

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
            val dateCheckIn = parsedDate(binding.etCheckIn.text.toString())
            val dateCheckOut = parsedDate(binding.etCheckOut.text.toString())

            if(!dateCheckIn.equals("error") && !dateCheckOut.equals("error")){
                viewModel.lookUpRoom(binding.tvAutoComplete.text.toString(), binding.etPeople.text.toString(), binding.etBudget.text.toString(), dateCheckIn, dateCheckOut, navigateToResults ={navigateToResults(it)})
            }
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

    fun parsedDate(date: String): String{
        val inputDf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val outputDf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val parsedDate:Date

        try{
            parsedDate = inputDf.parse(date)?: return "error"
        }catch (e: ParseException){
            showAlert()
            return "error"
        }
        return outputDf.format(parsedDate)
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Error")
        builder.setMessage("Fecha no valida")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    fun navigateToResults(rooms: List<RoomItem>){
        Log.i("POTATO", "HomeFragment viewModel.roomsList: $rooms")
        val mainActivity = activity as MainActivity
        mainActivity.navigateToResults(rooms)
    }

}