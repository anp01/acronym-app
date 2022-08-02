package com.example.acronym.api

import com.example.acronym.models.WordResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("dictionary.py")
    suspend fun fetchWordList(@Query("sf") shortForm: String): Response<WordResponse>
}