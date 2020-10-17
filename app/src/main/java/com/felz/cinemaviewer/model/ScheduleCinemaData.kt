package com.felz.cinemaviewer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScheduleCinemaData(
    @SerializedName("parent")
    @Expose
    var parent:String,
    @SerializedName("cinemas")
    @Expose
    var cinemas: ArrayList<ScheduleCinema>
)