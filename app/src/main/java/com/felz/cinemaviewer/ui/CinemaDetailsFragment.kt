package com.felz.cinemaviewer.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.felz.cinemaviewer.R
import com.felz.cinemaviewer.model.Movie
import com.felz.cinemaviewer.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cinema_details.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.time.format.DateTimeFormatter

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CinemaDetailsFragment : Fragment() , View.OnClickListener{
    private  val TAG = "CinemaDetailsFragment"
    private var navController: NavController? = null
    private val viewModel: CinemaDetailsViewModel by viewModels()
    private lateinit var theater:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setStateEvent(CinemaDetailsStateEvent.GetMovieEvent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cinema_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController=Navigation.findNavController(view)
        btnToSeatMap.setOnClickListener(this)
        subscribeObservers()
    }
    private fun subscribeObservers(){
        viewModel.dataStateMovie.observe(viewLifecycleOwner, Observer { dataState->
            when(dataState){
                is DataState.Success<Movie>->{
                    displayProgressBar(false)
                    Log.d(TAG,"Value:"+dataState.data.id)
                    renderScreen(dataState.data)
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

    private fun renderScreen(movie: Movie){
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(movie.poster)
            .into(poster)
        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(movie.posterLandscape)
            .into(poster_landscape)
        title.text = movie.title
        genre.text = movie.genre

        var movieTime = movie.runtime.split(".").toTypedArray()

        var hr = movieTime[0].toInt()/60
        var min:Int = 0
        if( movieTime[1].toInt()>0){
             min = movieTime[1].toInt()
        }
        var suffix="hrs"
        if(hr==1){
            suffix="hr"
        }
        var outputDuration="$hr $suffix"
        if(min>0){
            outputDuration="$hr $suffix $min Min/s"
        }
        runtime_duration.text =outputDuration
        advisory_rating.text =movie.advisory
        var outputCast:String = ""
        for (cast in movie.cast){
            outputCast="$outputCast $cast,"
        }

        cast.text = outputCast.substring(0,outputCast.length-1)
        release_date.text = convertDate(movie.releaseDate)
        synopsis.text = movie.synopsis
        theater=movie.theater
        Log.d(TAG,"Theater:"+movie.theater)
    }

    private fun convertDate(releaseDate:String):String{
        val firstDate = releaseDate
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val date = formatter.parse(firstDate)
        val desiredFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy").format(date)
        return desiredFormat;
    }

    private fun displayProgressBar(isDisplayed:Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }
    private fun displayError(message:String?){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnToSeatMap->{
                val bundle = bundleOf( "theater" to theater)
                navController?.navigate(R.id.action_cinemaDetailsFragment_to_seatFragment,bundle)
            }
        }
    }

}