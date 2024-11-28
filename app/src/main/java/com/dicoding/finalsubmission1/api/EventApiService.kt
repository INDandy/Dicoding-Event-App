package com.dicoding.finalsubmission1.api

import com.dicoding.finalsubmission1.data.EventDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventApiService {

    @GET("events/{id}")
    fun getEventDetail(@Path("id") eventId: Int): Call<EventDetail>




}