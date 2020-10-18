package com.felz.cinemaviewer.repository

import com.felz.cinemaviewer.model.Movie
import com.felz.cinemaviewer.model.Schedule
import com.felz.cinemaviewer.model.SeatMap
import com.felz.cinemaviewer.network.MovieNetworkMapper
import com.felz.cinemaviewer.network.MovieRetrofit
import com.felz.cinemaviewer.network.ScheduleNetworkMapper
import com.felz.cinemaviewer.network.SeatMapNetworkMapper
import com.felz.cinemaviewer.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception


class MainRepository constructor(
    private val retrofit: MovieRetrofit,
    private val networkMapper: MovieNetworkMapper,
    private val scheduleNetworkMapper: ScheduleNetworkMapper,
    private val seatMapNetworkMapper: SeatMapNetworkMapper
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
    suspend fun getSeat(): Flow<DataState<SeatMap>> = flow {
        emit(DataState.Loading)
        try{
            val networkSeatMap= retrofit.getSeat()
            val seatMap =seatMapNetworkMapper.mapFromEntity(networkSeatMap)
            emit(DataState.Success(seatMap))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

}