package com.dev.apprx.login.domain

import com.dev.apprx.login.data.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun  invoke(user:String, password:String):Boolean{
        return repository.doLogin(user, password)
    }
}