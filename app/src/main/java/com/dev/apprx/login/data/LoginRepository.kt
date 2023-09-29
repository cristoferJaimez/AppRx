package com.dev.apprx.login.data

import com.dev.apprx.login.data.network.LoginService

class LoginRepository {
    private val api = LoginService()
    //encargado de pedir base de datos, retrofill, etc
    suspend fun doLogin(user:String, password:String):Boolean{
        //puedo hacer comprobaciones
        return api.doLogin(user, password)
    }
}