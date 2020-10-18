package com.felz.cinemaviewer.model

data class SeatMap(
    var seatMap: ArrayList<ArrayList<String>>,
    var seatMapAvailable:SeatMapAvailable
) {
}