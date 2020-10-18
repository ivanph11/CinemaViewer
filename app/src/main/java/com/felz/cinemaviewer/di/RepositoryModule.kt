package com.felz.cinemaviewer.di

import com.felz.cinemaviewer.network.MovieNetworkMapper
import com.felz.cinemaviewer.network.MovieRetrofit
import com.felz.cinemaviewer.network.ScheduleNetworkMapper
import com.felz.cinemaviewer.network.SeatMapNetworkMapper
import com.felz.cinemaviewer.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule{
    @Singleton
    @Provides
    fun provideMainRepository(
        retrofit:MovieRetrofit,
        networkMapper:MovieNetworkMapper,
        networkMapperSchedule: ScheduleNetworkMapper,
        networkMapperSeat:SeatMapNetworkMapper
    ):MainRepository{
        return MainRepository(retrofit,networkMapper,networkMapperSchedule,networkMapperSeat)
    }


}