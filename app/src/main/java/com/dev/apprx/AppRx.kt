package com.dev.apprx

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppRx:Application() {
    //inyecciones de clases, para las activity, para los viewModels, las que no son; a clases  con provider
}