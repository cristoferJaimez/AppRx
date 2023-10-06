package com.dev.apprx

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.apprx.camera.ui.CameraScreen
import com.dev.apprx.gallery.ui.GalleryScreen
import com.dev.apprx.login.ui.LoginScreen
import com.dev.apprx.register.ui.RegisterScreen
import com.dev.apprx.login.ui.LoginViewModel
import com.dev.apprx.menu.ui.MenuScreen
import com.dev.apprx.ui.theme.AppRxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private  val loginViewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //LoginScreen(loginViewModel)
                    //RegisterScreen()
                    //CameraScreen()
                    GalleryScreen()
                    //MenuScreen()
                }
            }
        }
    }
}

