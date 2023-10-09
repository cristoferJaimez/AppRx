package com.dev.apprx.rout.navigate

sealed class AppScreens(val route: String){
    object SplashScreen : AppScreens("splash_screen")
    object LoginScreen : AppScreens("login_screen")
    object MenuScreen : AppScreens("menu_screen")
    object RegisterScreen : AppScreens("register_screen")
    object CameraScreen : AppScreens("camera_screen")
    object GalleryScreen : AppScreens("gallery_screen")

}
