package com.dev.apprx.rout

sealed class Screen(val route: String) {
    object Login : Screen("loginScreen")
    object Camera : Screen("cameraScreen")
    object Gallery : Screen("galleryScreen")
    object Register : Screen("RegisterScreen")

}
