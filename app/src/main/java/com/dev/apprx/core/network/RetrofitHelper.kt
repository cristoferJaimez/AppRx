package com.dev.apprx.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://192.1.1.127:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}