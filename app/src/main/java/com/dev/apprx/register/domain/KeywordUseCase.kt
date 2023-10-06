package com.dev.apprx.register.domain

import com.dev.apprx.login.data.LoginRepository
import javax.inject.Inject

class KeywordUseCase @Inject constructor() {
    private val repository = LoginRepository()
    suspend operator fun  invoke(user:String, password:String):Boolean{
        return repository.doLogin(user, password)
    }
}