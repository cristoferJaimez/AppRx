package com.dev.apprx.login.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.apprx.login.domain.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    val loginUseCase = LoginUseCase()


    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private  val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable : LiveData<Boolean> = _isLoginEnable

    //validar login button
    fun onLoginChanged(email: String, password: String){
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email = email, password = password)
    }


    fun enableLogin(email: String, password: String) = password.length > 5 && email.length > 1

    fun onLoginSelected(){
        viewModelScope.launch {
            val result = loginUseCase(email.value!!, password.value!!)
            if(result){
                //navegar a la siguiente activity
                Log.i("cristo", "result ok")
            }
        }
    }
}