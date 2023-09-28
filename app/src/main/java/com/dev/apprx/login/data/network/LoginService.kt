package com.dev.apprx.login.data.network

import com.dev.apprx.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {
   private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(user:String, password:String):Boolean{
        return withContext(Dispatchers.IO){
           val response = retrofit.create(LoginClient::class.java).doLogin(user, password)
           response.body()?.success ?: false
        }
    }
}