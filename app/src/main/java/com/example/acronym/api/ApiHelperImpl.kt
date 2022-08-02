package com.example.acronym.api

import com.example.acronym.models.WordResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun fetchWordList(shortForm: String): Response<WordResponse> {
        return apiService.fetchWordList(shortForm)
    }
}