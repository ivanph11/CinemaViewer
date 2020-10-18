package com.felz.cinemaviewer.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felz.cinemaviewer.R
import com.felz.cinemaviewer.callback.OnItemClickListener
import com.felz.cinemaviewer.model.ScheduleDate
import kotlinx.android.synthetic.main.fragment_seat.*
import kotlinx.android.synthetic.main.item_seat_col.view.*
import kotlinx.android.synthetic.main.item_seat_date.view.*
import kotlinx.android.synthetic.main.item_seat_row.view.*

class SeatMapColAdapter(
    private val listener: OnItemClickListener
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private  val TAG = "SeatMapColAdapter"
    private var items: List<String> = ArrayList()
    private var availableSeats: List<String> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SeatDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_seat_col,parent,false)
        )
    }
    fun submitList(itemList:List<String>,seatAvailable:List<String>){
        Log.d(TAG,"List Received:"+itemList.size)
        items=itemList
        availableSeats=seatAvailable
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SeatDateViewHolder->{
                holder.bind(items.get(position),position)
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }

    inner class SeatDateViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        var btnCol=itemView.btn_col
        fun bind(col:String,position: Int){
            if(col.equals("a(30)",true)){
                btnCol.visibility=View.INVISIBLE
            }
            if(!availableSeats.contains(col)){
                btnCol.isEnabled=false
            }
//            if(col.equals("F19")){
//                btnCol.isEnabled=false
//            }
            btnCol.setOnClickListener{view->
                listener.onMapClick(col)
               view.isSelected = !view.isSelected
            }
        }

    }

}