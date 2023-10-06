import androidx.navigation.NavHostController
import com.dev.apprx.rout.Screen

class NavActions(navController: NavHostController) {
    val navigateToDetail: () -> Unit = {
        navController.navigate(Screen.Login.route)
    }
}
