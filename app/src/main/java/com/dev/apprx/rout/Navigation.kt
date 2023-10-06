package com.dev.apprx.rout

import NavActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dev.apprx.camera.ui.CameraScreen
import com.dev.apprx.gallery.ui.GalleryScreen
import com.dev.apprx.login.domain.LoginUseCase
import com.dev.apprx.login.ui.LoginScreen
import com.dev.apprx.login.ui.LoginViewModel
import com.dev.apprx.register.ui.RegisterScreen
import com.dev.apprx.rout.Screen.*

class Navigation {

    @Composable
    fun SetupNavigation() {
        val navController = rememberNavController()
        val navActions = NavActions(navController)
        val actions = remember(navController) { navActions }

        NavHost(
            navController = navController,
            startDestination = Camera.route // Utiliza la ruta desde el enum
        ) {
            try {
                composable(Login.route) {
                    LoginScreen(LoginViewModel(loginUseCase = LoginUseCase()))
                }
                composable(Camera.route) {
                    CameraScreen()
                }
                composable(Gallery.route) {
                    GalleryScreen()
                }
                composable(Register.route) {
                    RegisterScreen()
                }
                // Puedes agregar más composables para otras pantallas aquí si es necesario
            } catch (e: Exception) {
                // Captura cualquier excepción y muestra información en el logcat
                e.printStackTrace()

            }
        }
    }
}
