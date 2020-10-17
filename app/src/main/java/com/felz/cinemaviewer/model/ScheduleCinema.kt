package com.felz.cinemaviewer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScheduleCinema(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("cinema_id")
    @Expose
    var cinemaId: String,
    @SerializedName("label")
    @Expose
    var label: String
)