package com.example.acronym.api

import com.example.acronym.models.WordResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun fetchWordList(shortForm: String): Response<WordResponse>
}