package com.dev.apprx.login.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.apprx.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private var loginUseCase: LoginUseCase) :ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private  val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable : LiveData<Boolean> = _isLoginEnable

    private  val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    //validar login button
    fun onLoginChanged(email: String, password: String){
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email = email, password = password)
    }


    private fun enableLogin(email: String, password: String) = password.length > 5 && email.length > 1


    //ejecutar login
    fun onLoginSelected(){
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase(email.value!!, password.value!!)
            if(result){
                //navegar a la siguiente activity
                Log.i("btn", "push btn  log in result ok")
            }else{
                Log.i("btn", "push btn  log in result fail")
            }
            _isLoading.value = false
        }
    }

    //ejecutar boton de registro
    fun onRegisterSelect(){
        viewModelScope.launch {
            Log.i("btn", "push btn register!!!")
        }
    }
}