package com.felz.cinemaviewer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScheduleTimeData(
    @SerializedName("parent")
    @Expose
    var parent:String,
    @SerializedName("times")
    @Expose
    var times: ArrayList<ScheduleTime>
)