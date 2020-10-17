package com.felz.cinemaviewer.ui

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felz.cinemaviewer.R
import com.felz.cinemaviewer.callback.OnItemClickListener
import com.felz.cinemaviewer.model.ScheduleCinema
import com.felz.cinemaviewer.model.ScheduleDate
import kotlinx.android.synthetic.main.item_seat_date.view.*

class SeatCinemaAdapter(
    private val listener: OnItemClickListener
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private  val TAG = "SeatCinemaAdapter"
    private var items: List<ScheduleCinema> = ArrayList()
    private var selected=0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SeatDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_seat_date,parent,false)
        )
    }
    fun submitList(itemList:List<ScheduleCinema>){
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
        val txtDate = itemView.txt_date
        val txtDay = itemView.txt_day

        fun bind(cinema:ScheduleCinema,position: Int){
            itemView.isSelected = position == selected
            txtDate.visibility=View.GONE
            txtDay.setText(cinema.label)
            itemView.setOnClickListener{
                selected=position
                it.isSelected=true
                listener.onCinemaClick(cinema)
                notifyDataSetChanged()
            }
        }
    }

}