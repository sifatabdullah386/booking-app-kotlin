package com.example.bookingapp.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClientInstance {
    private const val BASE_URL = "https://hotelmgtapi.cisstaging.com/api/"

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MyAPIServices {
        return retrofit.create(MyAPIServices::class.java)
    }
}
