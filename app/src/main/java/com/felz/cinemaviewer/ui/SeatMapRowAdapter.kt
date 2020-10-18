package com.felz.cinemaviewer.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felz.cinemaviewer.R
import com.felz.cinemaviewer.callback.OnItemClickListener
import com.felz.cinemaviewer.model.ScheduleDate
import kotlinx.android.synthetic.main.fragment_seat.*
import kotlinx.android.synthetic.main.item_seat_date.view.*
import kotlinx.android.synthetic.main.item_seat_row.view.*

class SeatMapRowAdapter(
    private val listener: OnItemClickListener
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private  val TAG = "SeatMapRowAdapter"
    private var items: List<List<String>> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SeatDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_seat_row,parent,false)
        )
    }
    fun submitList(itemList:List<List<String>>){
        Log.d(TAG,"List Received:"+itemList.size)
        items=itemList
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
        val txtRight = itemView.txt_right
        val txtLeft = itemView.txt_left
        val recyclerViewCol = itemView.recycler_view_col

        private lateinit var seatMapColAdapter: SeatMapColAdapter

        fun bind(row:List<String>,position: Int){
            initRecyclerViewMapRow()
            var label = row.get(0).substring(0,1)
            txtRight.setText(label.toUpperCase())
            txtLeft.setText(label.toUpperCase())
            seatMapColAdapter.submitList(row)
            itemView.setOnClickListener{
                it.isSelected=true
                notifyDataSetChanged()
            }
        }
        private fun initRecyclerViewMapRow(){
            recyclerViewCol.apply {
                layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
                seatMapColAdapter = SeatMapColAdapter(listener)
                adapter = seatMapColAdapter
            }
        }
    }

}