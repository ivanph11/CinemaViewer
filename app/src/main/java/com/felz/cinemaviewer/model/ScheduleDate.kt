package com.felz.cinemaviewer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScheduleDate(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("label")
    @Expose
    var label: String,
    @SerializedName("date")
    @Expose
    var date: String
)