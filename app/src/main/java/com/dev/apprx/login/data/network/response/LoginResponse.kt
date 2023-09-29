package com.dev.apprx.login.data.network.response

import com.google.gson.annotations.SerializedName

//lo que me responde la api
data class LoginResponse (@SerializedName("success") val success:Boolean)