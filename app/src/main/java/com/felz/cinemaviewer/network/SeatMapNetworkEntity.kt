package com.felz.cinemaviewer.network

import com.felz.cinemaviewer.model.SeatMapAvailable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SeatMapNetworkEntity(
    @SerializedName("seatmap")
    @Expose
    var seatMap: ArrayList<ArrayList<String>>,
    @SerializedName("available")
    @Expose
    var seatMapAvailable: SeatMapAvailable
) {
}