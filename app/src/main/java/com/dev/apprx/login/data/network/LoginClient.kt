package com.dev.apprx.login.data.network

import com.dev.apprx.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginClient {
    @POST("/auth/login")
    suspend fun doLogin(user:String, password:String):Response<LoginResponse>
}