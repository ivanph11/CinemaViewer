package com.felz.cinemaviewer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScheduleTime(
    @SerializedName("id")
    @Expose
    var id:String,
    @SerializedName("label")
    @Expose
    var label: String,
    @SerializedName("schedule_id")
    @Expose
    var scheduleId: String,
    @SerializedName("popcorn_price")
    @Expose
    var popcornPrice:String,
    @SerializedName("popcorn_label")
    @Expose
    var popcornLabel:String,
    @SerializedName("seating_type")
    @Expose
    var seatingType:String,
    @SerializedName("price")
    @Expose
    var price:String
)