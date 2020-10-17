package com.felz.cinemaviewer.model

data class Schedule(
    var dates: ArrayList<ScheduleDate>,
    var cinemas: ArrayList<ScheduleCinemaData>,
    var times: ArrayList<ScheduleTimeData>
) {
}