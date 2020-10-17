package com.felz.cinemaviewer.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.felz.cinemaviewer.R
import com.felz.cinemaviewer.callback.OnItemClickListener
import com.felz.cinemaviewer.model.*
import com.felz.cinemaviewer.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cinema_details.*
import kotlinx.android.synthetic.main.fragment_cinema_details.progress_bar
import kotlinx.android.synthetic.main.fragment_seat.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.Exception

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SeatFragment : Fragment(),OnItemClickListener {
    private  val TAG = "SeatFragment"
    private var navController: NavController? = null
    private val viewModel: SeatViewModel by viewModels()
    private lateinit var theater :String
    private lateinit var dateAdapter: SeatDateAdapter
    private lateinit var cinemaAdapter: SeatCinemaAdapter
    private lateinit var timeAdapter: SeatTimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        theater = requireArguments().getString("theater").toString()
        viewModel.setStateEvent(SeatViewModelEvent.GetSchedule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        initView()
        subscribeObservers()
        initRecyclerViewDate()
        initRecyclerViewCinema()
        initRecyclerViewTime()
    }
    private fun initView(){
        cinema.text=theater
    }
    private var updatedCinemaList= arrayListOf<ScheduleCinema>()
    private var defaultCinemaList= arrayListOf<ScheduleCinema>()
    private var defaultTimeList= arrayListOf<ScheduleTime>()
    private var updatedTimeList= arrayListOf<ScheduleTime>()
    lateinit  var defaultId:String
    private fun subscribeObservers(){
        viewModel.dataStateSchedule.observe(viewLifecycleOwner, Observer { dataState->
            when(dataState){
                is DataState.Success<Schedule>->{
                    displayProgressBar(false)
                    Log.d(TAG,"Value:"+dataState.data.dates.size)
                    dateAdapter.submitList(dataState.data.dates)
                    defaultCinemaList.addAll(dataState.data.cinemas.get(0).cinemas)
                    defaultTimeList.addAll(dataState.data.times.get(0).times)
                    val dateId=dataState.data.dates.get(0).id
                    updateCinemaList(dateId)

                    val cinemaId=dataState.data.cinemas.get(0).cinemas.get(0).cinemaId
                    updateTimeList(cinemaId+"::"+dateId)

                    Log.d(TAG,"TimeListSize:"+defaultTimeList.size)
                    cinemaAdapter.submitList(updatedCinemaList)
                    //renderScreen(dataState.data)

                }
                is DataState.Error->{
                    displayProgressBar(false)
                    displayError(dataState.exception.message)

                }
                is DataState.Loading->{
                    displayProgressBar(true)
                }
            }
        })
    }
    private fun updateCinemaList(dateId:String){
        updatedCinemaList= arrayListOf<ScheduleCinema>()
        for(cinema in defaultCinemaList){
            var cinemaId=cinema.id.split("-")[0]
            if(dateId.equals(cinemaId)){
                Log.d(TAG,"cinemaId:"+cinema.id)
                updatedCinemaList.add(cinema)
            }else{
                Log.d(TAG,"cinemaId:else")
            }
        }
        cinemaAdapter.submitList(updatedCinemaList)
        updateTimeList(updatedCinemaList.get(0).cinemaId+"::"+dateId)
    }
    private fun updateTimeList(schedule:String){
        updatedTimeList= arrayListOf<ScheduleTime>()
        for(time in defaultTimeList){
            var timeCinemaId=time.scheduleId.split(",")[0]
            Log.d(TAG,"TIME:"+timeCinemaId+"=="+schedule)
            if(schedule == timeCinemaId){
                updatedTimeList.add(time)
            }else{
                Log.d(TAG,"TIME:else")
            }
        }
        timeAdapter.submitList(updatedTimeList)
        try{
            if(updatedTimeList.size>0){
                updateTotal(updatedTimeList.get(0).price.toInt())
            }else{
                updateTotal(0)
                Toast.makeText(context,"No Schedule Available",Toast.LENGTH_SHORT).show()
            }
        }catch (e: Exception){

        }
    }
    private fun updateTotal(totalPrice:Int){
        total.text="₱"+totalPrice.toString()
    }
    private fun initRecyclerViewDate(){
        recycler_view_date.apply {
            layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL ,false)
            dateAdapter = SeatDateAdapter(this@SeatFragment)
            adapter = dateAdapter
        }
    }
    private fun initRecyclerViewCinema(){
        recycler_view_cinema.apply {
            layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL ,false)
            cinemaAdapter = SeatCinemaAdapter(this@SeatFragment)
            adapter = cinemaAdapter
        }
    }
    private fun initRecyclerViewTime(){
        recycler_view_time.apply {
            layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL ,false)
            timeAdapter = SeatTimeAdapter(this@SeatFragment)
            adapter = timeAdapter
        }
    }
    private fun displayProgressBar(isDisplayed:Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }
    private fun displayError(message:String?){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(date: ScheduleDate) {
        updateCinemaList(date.id)
    }
    override fun onCinemaClick(cinema: ScheduleCinema) {
        updateTimeList(cinema.cinemaId+"::"+cinema.id.split("-")[0])
    }

    override fun onTimeClick(schedule: ScheduleTime) {
        updateTotal(schedule.price.toInt())
    }
}