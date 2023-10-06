package com.dev.apprx.register.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class keywordViewModel : ViewModel()  {

    private val _keyword = MutableLiveData<String>()
    val keyword : LiveData<String> = _keyword


    private  val _isKeywordEnable = MutableLiveData<Boolean>()
    val isKeywordEnable : LiveData<Boolean> = _isKeywordEnable

    fun onKeyChanged(key: String){
        _keyword.value = key
        Log.i("keyText", key)
        _isKeywordEnable.value = enableKeywordPass(key)
        Log.i("isKey", " ${_isKeywordEnable.value}")
    }

    private fun enableKeywordPass(key: String): Boolean? {
        return key.length >= 5
    }
}