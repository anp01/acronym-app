package com.example.acronym.di

import com.example.acronym.BuildConfig
import com.example.acronym.api.ApiHelper
import com.example.acronym.api.ApiHelperImpl
import com.example.acronym.api.ApiService
import com.example.acronym.others.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideOkhttpClient() = if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    } else
        OkHttpClient.Builder()
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl
}