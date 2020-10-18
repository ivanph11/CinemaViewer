package com.felz.cinemaviewer.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.felz.cinemaviewer.R
import com.felz.cinemaviewer.callback.OnItemClickListener
import com.felz.cinemaviewer.model.ScheduleDate
import com.felz.cinemaviewer.model.ScheduleTime
import kotlinx.android.synthetic.main.item_seat_date.view.*

class SeatTimeAdapter(
    private val listener: OnItemClickListener
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private  val TAG = "SeatDateAdapter"
    private var items: List<ScheduleTime> = ArrayList()
    private var selected=0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SeatDateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_seat_date,parent,false)
        )
    }
    fun submitList(itemList:List<ScheduleTime>){
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

        fun bind(time:ScheduleTime,position: Int){
            itemView.isSelected = position == selected
            txtDate.setText("₱"+time.price)
            txtDay.setText(time.label)
            itemView.setOnClickListener{
                MaterialDialog(itemView.context).show {
                    message(text = "Selecting time will reset the seat/s selected. Do you want to continue?")
                    positiveButton(R.string.agree){dialog->
                        selected=position
                        it.isSelected=true
                        listener.onTimeClick(time)
                        notifyDataSetChanged()
                    }
                    negativeButton(R.string.disagree)
                }

            }
        }
    }

}