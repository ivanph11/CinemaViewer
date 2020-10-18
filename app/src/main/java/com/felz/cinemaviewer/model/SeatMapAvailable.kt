package com.felz.cinemaviewer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SeatMapAvailable(
    @SerializedName("seats")
    @Expose
    var seats:ArrayList<String>
)