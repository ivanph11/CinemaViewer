package com.felz.cinemaviewer.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.felz.cinemaviewer.model.Movie
import com.felz.cinemaviewer.repository.MainRepository
import com.felz.cinemaviewer.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CinemaDetailsViewModel
@ExperimentalCoroutinesApi
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private  val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _dataStateMovie: MutableLiveData<DataState<Movie>> = MutableLiveData()
    val dataStateMovie : LiveData<DataState<Movie>> get() = _dataStateMovie

    fun setStateEvent(event: CinemaDetailsStateEvent){
        viewModelScope.launch {
            when(event){
                is CinemaDetailsStateEvent.GetMovieEvent->{
                    mainRepository.getMovie()
                        .onEach { dataState ->
                            _dataStateMovie.value=dataState
                        }.launchIn(viewModelScope)
                }
            }
        }
    }
}
sealed class CinemaDetailsStateEvent{
    object GetMovieEvent: CinemaDetailsStateEvent()
}