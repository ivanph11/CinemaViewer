package com.felz.cinemaviewer.di

import com.felz.cinemaviewer.network.MovieRetrofit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson:Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("http://ec2-52-76-75-52.ap-southeast-1.compute.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): MovieRetrofit{
        return  retrofit
            .build()
            .create(MovieRetrofit::class.java)
    }
}