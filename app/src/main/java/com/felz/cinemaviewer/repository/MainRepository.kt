package com.felz.cinemaviewer.repository

import com.felz.cinemaviewer.model.Movie
import com.felz.cinemaviewer.model.Schedule
import com.felz.cinemaviewer.network.MovieNetworkMapper
import com.felz.cinemaviewer.network.MovieRetrofit
import com.felz.cinemaviewer.network.ScheduleNetworkMapper
import com.felz.cinemaviewer.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception


class MainRepository constructor(
    private val retrofit: MovieRetrofit,
    private val networkMapper: MovieNetworkMapper,
    private val scheduleNetworkMapper: ScheduleNetworkMapper
){
    suspend fun getMovie(): Flow<DataState<Movie>> = flow {
        emit(DataState.Loading)
        try{
            val networkMovie= retrofit.getMovie()
            val movie =networkMapper.mapFromEntity(networkMovie)
            emit(DataState.Success(movie))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
    suspend fun getSchedule(): Flow<DataState<Schedule>> = flow {
        emit(DataState.Loading)
        try{
            val networkSchedule= retrofit.getSchedule()
            val schedule =scheduleNetworkMapper.mapFromEntity(networkSchedule)
            emit(DataState.Success(schedule))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}