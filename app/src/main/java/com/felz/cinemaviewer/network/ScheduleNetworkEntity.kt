package com.felz.cinemaviewer.network

import com.felz.cinemaviewer.model.ScheduleCinemaData
import com.felz.cinemaviewer.model.ScheduleDate
import com.felz.cinemaviewer.model.ScheduleTimeData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScheduleNetworkEntity(
    @SerializedName("dates")
    @Expose
    var dates: ArrayList<ScheduleDate>,
    @SerializedName("cinemas")
    @Expose
    var cinemas: ArrayList<ScheduleCinemaData>,
    @SerializedName("times")
    @Expose
    var times: ArrayList<ScheduleTimeData>
) {
}