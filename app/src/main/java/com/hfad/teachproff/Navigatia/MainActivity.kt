package com.hfad.teachproff.Navigatia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hfad.teachproff.AboutShoose
import com.hfad.teachproff.Basket
import com.hfad.teachproff.CategoriiShoos
import com.hfad.teachproff.Favorit
import com.hfad.teachproff.Home.Home
import com.hfad.teachproff.LogIn_SigIn.LogIn
import com.hfad.teachproff.Home.Onboarding
import com.hfad.teachproff.LogIn_SigIn.SigIn
import com.hfad.teachproff.Poisk
import com.hfad.teachproff.Popular
import com.hfad.teachproff.ui.theme.TeachProffTheme

sealed class Screen(val route: String){
    object Onboarding: Screen("onboarding")
    object LogIN: Screen("login")
    object Home: Screen("home")
    object Favorite: Screen("favorite")
    object Popular: Screen("popular")
    object CategoriiShoos: Screen("category_screen/{category}") {
        fun createRoute(category: String) = "category_screen/$category"
    }
    object AboutShoose: Screen("aboutshoose")
    object Poisk: Screen("poisk")
    object Basket: Screen("basket")
    object SigIn: Screen("sigIn")
}
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TeachProffTheme {
                NavController()
            }
        }
    }
}
@Composable
fun NavController(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SigIn.route,
        modifier = Modifier.background(Color.Transparent)

    ) {
        composable(Screen.Onboarding.route) {
            Onboarding(navController)
        }
        composable(Screen.LogIN.route) {
            LogIn(navController)
        }
        composable(Screen.Home.route) {
            Home(navController)
        }
        composable(Screen.Favorite.route) {
            Favorit(navController)
        }
        composable(Screen.Popular.route) {
            Popular(navController)
        }
        // Определение композируемого экрана для навигации.
// 'composable' используется для определения нового экрана в навигации.
// 'route' указывает маршрут, который будет использован для этого экрана.
        composable(
            route = Screen.CategoriiShoos.route,
            arguments = listOf(navArgument("category"){
                type = NavType.StringType
            })
        ) { c ->
            CategoriiShoos(navController)

        }
        composable(Screen.AboutShoose.route) {
            AboutShoose(navController)
        }
        composable(Screen.Poisk.route) {
            Poisk(navController)
        }
        composable(Screen.Basket.route) {
            Basket(navController)
        }
        composable(Screen.SigIn.route) {
            SigIn(navController)
        }
    }
}