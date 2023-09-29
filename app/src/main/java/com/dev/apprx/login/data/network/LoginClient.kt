package com.dev.apprx.login.data.network

import com.dev.apprx.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface LoginClient {

    //point de api corrutinas
    @POST("/auth/login")
    suspend fun doLogin():Response<LoginResponse>
}