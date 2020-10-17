package com.felz.cinemaviewer.callback

import com.felz.cinemaviewer.model.ScheduleCinema
import com.felz.cinemaviewer.model.ScheduleDate
import com.felz.cinemaviewer.model.ScheduleTime

interface  OnItemClickListener {
    fun onItemClick(date: ScheduleDate)
    fun onCinemaClick(cinema: ScheduleCinema)
    fun onTimeClick(cinema: ScheduleTime)
}