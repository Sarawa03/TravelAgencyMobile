package com.sara.travelagency.ui.view.results.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sara.travelagency.R
import com.sara.travelagency.domain.model.RoomItem
import javax.inject.Inject

class ResultAdapter @Inject constructor(
    private val onItemSelected: (String) -> Unit,
    private val dateCheckIn: String,
    private val dateCheckOut: String
): RecyclerView.Adapter<ResultViewHolder>(){

    var roomList: List<RoomItem> = emptyList()

    fun updateList(roomList: List<RoomItem>){
        this.roomList = roomList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.results_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(roomList[position], dateCheckIn, dateCheckOut ,onItemSelected)
    }


}