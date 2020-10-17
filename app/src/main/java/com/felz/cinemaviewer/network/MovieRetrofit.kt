package com.felz.cinemaviewer.network

import retrofit2.http.GET

interface MovieRetrofit {
    @GET("movie.json")
    suspend fun getMovie(): MovieNetworkEntity
}