package com.dev.apprx.rout.navigate

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.apprx.camera.ui.CameraScreen
import com.dev.apprx.camera.ui.CameraViewModel
import com.dev.apprx.gallery.ui.GalleryScreen
import com.dev.apprx.login.domain.LoginUseCase
import com.dev.apprx.login.ui.LoginScreen
import com.dev.apprx.login.ui.LoginViewModel
import com.dev.apprx.menu.ui.MenuScreen
import com.dev.apprx.register.ui.RegisterScreen
import com.dev.apprx.splashScreen.SplashScreen
import kotlin.math.roundToInt


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route){

        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(loginViewModel = LoginViewModel(LoginUseCase()), navController)
        }
        composable(route = AppScreens.CameraScreen.route){
            CameraScreen(viewModel = CameraViewModel(), navController)
        }

        composable(route = AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }

        composable(route = AppScreens.MenuScreen.route) { backStackEntry ->
            val currentScreen = remember { AppScreens.MenuScreen }

            val transition = updateTransition(
                targetState = currentScreen,
                label = "menuTransition"
            )

            val translationX by transition.animateFloat(
                label = "menuTransitionTranslationX"
            ) { screen ->
                when (screen) {
                    AppScreens.MenuScreen -> 0f // No desplazamiento en la entrada
                    else -> 500f // Desplazamiento desde la izquierda en la salida
                }
            }

            // Aplica la animación de desplazamiento
            val modifier = Modifier
                .offset { IntOffset(-translationX.roundToInt(), 0) }

            // Contenido de la pantalla
            MenuScreen(navController, modifier)
        }



        composable(route = AppScreens.RegisterScreen.route){
            RegisterScreen()
        }

        composable(route = AppScreens.GalleryScreen.route){
            val context = LocalContext.current
            GalleryScreen(navController, context)
        }
    }
}