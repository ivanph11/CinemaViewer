package com.felz.cinemaviewer.model

data class Movie (
    var id: String,
    var title: String,
    var poster: String,
    var genre:String,
    var advisory:String,
    var posterLandscape: String,
    var releaseDate: String,
    var runtime: String,
    var synopsis: String,
    var variants: ArrayList<String>,
    var theater: String,
    var cast: ArrayList<String>
){
}