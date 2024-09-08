package com.example.medai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medai.admin.AdminHomePage
import com.example.medai.admin.crud.Login
import com.example.medai.admin.crud.SignUp
import com.example.medai.db.Screens
import com.example.medai.ui.theme.MedAiTheme
import com.example.medai.user.BookAppointment
import com.example.medai.user.BookLabTest
import com.example.medai.user.CartScreen
import com.example.medai.user.MainScreen
import com.example.medai.user.RecordActivities
import com.example.medai.user.SearchProducts
import com.example.medai.viewmodels.MainViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedAiTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                val mainViewModel by lazy { MainViewModel() }
                val navController = rememberNavController()
                App(mainViewModel, navController)
            }
        }
    }
}


@Composable
fun App(mainViewModel: MainViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.LogIn.name) {

        composable(route = Screens.LogIn.name) {
            Login(navController, mainViewModel)
        }
        composable(route = Screens.SignUp.name) {
            SignUp(navController, mainViewModel)
        }
        composable(route = Screens.MainScreen.name) {
            MainScreen(navController, mainViewModel)
        }
        composable(route = Screens.BookAppointmentScreen.name) {
            BookAppointment(navController, mainViewModel)
        }
        composable(route = Screens.BookLabTestScreen.name) {
            BookLabTest(navController, mainViewModel)
        }
        composable(route = Screens.RecordActivitiesScreen.name) {
            RecordActivities(navController, mainViewModel)
        }
        composable(route = Screens.SearchProductsScreen.name) {
            SearchProducts(navController, mainViewModel)
        }
        composable(route = Screens.AdminHomePage.name) {
            AdminHomePage(navController, mainViewModel)
        }
        composable(route = Screens.CartScreen.name) {
            CartScreen(navController, mainViewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App(mainViewModel = MainViewModel(), navController = rememberNavController())
}