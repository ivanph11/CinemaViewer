package com.felz.cinemaviewer.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.felz.cinemaviewer.model.Movie
import com.felz.cinemaviewer.model.Schedule
import com.felz.cinemaviewer.repository.MainRepository
import com.felz.cinemaviewer.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SeatViewModel
@ExperimentalCoroutinesApi
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val _dataStateSchedule: MutableLiveData<DataState<Schedule>> = MutableLiveData()
    val dataStateSchedule: LiveData<DataState<Schedule>> get() =_dataStateSchedule

    fun setStateEvent(event: SeatViewModelEvent){
        viewModelScope.launch {
            when(event){
                is SeatViewModelEvent.GetSchedule ->{
                    mainRepository.getSchedule()
                        .onEach { dataState ->
                            _dataStateSchedule.value=dataState
                        }.launchIn(viewModelScope)
                }
            }
        }
    }

}
sealed class SeatViewModelEvent{
    object GetSchedule: SeatViewModelEvent()
}