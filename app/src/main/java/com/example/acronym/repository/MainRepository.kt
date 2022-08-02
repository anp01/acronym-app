package com.example.acronym.repository

import com.example.acronym.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun fetchWordList(shortForm: String) = apiHelper.fetchWordList(shortForm)
}