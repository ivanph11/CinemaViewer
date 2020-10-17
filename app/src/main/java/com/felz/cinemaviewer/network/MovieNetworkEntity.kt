package com.felz.cinemaviewer.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieNetworkEntity(
    @SerializedName("movie_id")
    @Expose
    var id: String,
    @SerializedName("canonical_title")
    @Expose
    var title:String,
    @SerializedName("poster")
    @Expose
    var poster: String,
    @SerializedName("genre")
    @Expose
    var genre:String,
    @SerializedName("advisory_rating")
    @Expose
    var advisory:String,
    @SerializedName("poster_landscape")
    @Expose
    var posterLandscape: String,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String,

    @SerializedName("runtime_mins")
    @Expose
    var runtime: String,
    @SerializedName("synopsis")
    @Expose
    var synopsis: String,
    @SerializedName("variants")
    @Expose
    var variants: ArrayList<String>,
    @SerializedName("theater")
    @Expose
    var theater: String,
    @SerializedName("cast")
    @Expose
    var cast: ArrayList<String>
){}