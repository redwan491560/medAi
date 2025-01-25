package com.example.medai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.medai.admin.crud.ArticleCrud
import com.example.medai.admin.crud.Login
import com.example.medai.admin.crud.MedicineCrud
import com.example.medai.admin.crud.SignUp
import com.example.medai.db.DbCRUD
import com.example.medai.db.Status
import com.example.medai.db.ViewArticleScreen
import com.example.medai.db.ViewMedicineScreen
import com.example.medai.ui.theme.MedAiTheme
import com.example.medai.ui.theme.navScreens.AccountScreen
import com.example.medai.ui.theme.navScreens.DevelopersScreen
import com.example.medai.ui.theme.navScreens.ReportBugScreen
import com.example.medai.ui.theme.navScreens.SecurityScreen
import com.example.medai.ui.theme.navScreens.SettingsScreen
import com.example.medai.ui.theme.viewers.ArticleViewerScreen
import com.example.medai.ui.theme.viewers.MedicineViewerScreen
import com.example.medai.user.AiScreen
import com.example.medai.user.AiViewmodel
import com.example.medai.user.BookAppointment
import com.example.medai.user.BookLabTest
import com.example.medai.user.CartScreen
import com.example.medai.user.MainScreen
import com.example.medai.user.RecordActivities
import com.example.medai.user.SearchProducts
import com.example.medai.user_doctor.DoctorHomepageScreen
import com.example.medai.user_doctor.ManageAppointmentScreen
import com.example.medai.user_doctor.ManageScheduleScreen
import com.example.medai.util.Routes
import com.example.medai.viewmodels.AuthViewModel
import com.example.medai.viewmodels.DatabaseViewModel
import com.example.medai.viewmodels.MainViewModel
import com.example.medai.viewmodels.item.ArticleViewmodel
import com.example.medai.viewmodels.item.MedicineViewmodel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val db = Firebase.firestore
            MedAiTheme(
                darkTheme = false, dynamicColor = false
            ) {
                val authViewmodel: AuthViewModel = viewModel()
                val mainViewModel: MainViewModel = viewModel()
                val viewModel = DatabaseViewModel()
                val aiViewModel = AiViewmodel()

                App(mainViewModel, viewModel, aiViewModel, authViewmodel)
            }
        }
    }
}


@Composable
fun App(
    mainViewModel: MainViewModel,
    viewModel: DatabaseViewModel,
    aiViewmodel: AiViewmodel,
    authViewmodel: AuthViewModel
) {
    val navController = rememberNavController()
    val authState = authViewmodel.authState.observeAsState()


    val articleViewmodel = viewModel<ArticleViewmodel>()
    val medicineViewmodel = viewModel<MedicineViewmodel>()


    NavHost(
        navController = navController, startDestination = Routes.Splash
    ) {

        composable<Routes.Splash> {

            LaunchedEffect(
                authState.value
            ) {
                if (authState.value == Status.Authenticated) {
                    delay(1000)
                    navController.navigate(Routes.Main) {
                        popUpTo(Routes.Splash) {
                            inclusive = true
                        }
                    }
                } else {
                    delay(1000)
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Splash) {
                            inclusive = true
                        }
                    }
                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }


        composable<Routes.Login> {
            Login(navController, authViewmodel)
        }
        composable<Routes.Register> {
            SignUp(navController, authViewmodel)
        }
        composable<Routes.Main> {
            LaunchedEffect(
                authState.value
            ) {
                if (authState.value == Status.NotAuthenticated) {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Main) {
                            inclusive = true
                        }
                    }
                }
            }
            MainScreen(navController, medicineViewmodel, articleViewmodel, viewModel, authViewmodel)
        }


        composable<Routes.BookAppointmentScreen> {
            BookAppointment(navController, mainViewModel)
        }
        composable<Routes.BookLabTestScreen> {
            BookLabTest(navController, mainViewModel, authViewmodel)
        }
        composable<Routes.RecordActivities> {
            RecordActivities(navController, mainViewModel)
        }
        composable<Routes.SearchProductsScreen> {
            SearchProducts(navController, mainViewModel)
        }

        composable<Routes.Cart> {
            CartScreen(navController, mainViewModel)
        }


        // viewers
        composable<Routes.ArticleViewer> {
            val args = it.toRoute<Routes.ArticleViewer>()
            ArticleViewerScreen(
                title = args.title,
                description = args.description,
                author = args.author,
                uid = args.uid,
                story = args.story
            )
        }
        composable<Routes.MedicineViewer> {
            val args = it.toRoute<Routes.MedicineViewer>()
            MedicineViewerScreen(args, navController)
        }


        // crud

//        composable<Routes.CheckAdmin> {
//            val approval = remember { mutableStateOf(false) }
//            val currentUser by authViewmodel.currentUser.collectAsState()
//
//            LaunchedEffect(currentUser) {
//                if (currentUser != null) {
//                    val isAdmin = authViewmodel.checkAdminStatus(authViewmodel.currentUser)
//                    approval.value = isAdmin
//                    if (isAdmin) {
//                        navController.navigate(Routes.CRUD) {
//                            popUpTo(Routes.Main) { inclusive = true }
//                        }
//                    } else {
//                        navController.navigate(Routes.Main)
//                    }
//                }
//            }
//        }


        composable<Routes.CRUD> {
            DbCRUD(authViewmodel, navController)
        }

        composable<Routes.ArticleCrud> {
            ArticleCrud(articleViewmodel = articleViewmodel, navController = navController)
        }
        composable<Routes.ViewArticle> {
            ViewArticleScreen(articleViewmodel = articleViewmodel, navController = navController)
        }

        composable<Routes.ViewMedicine> {
            ViewMedicineScreen(medicineViewmodel = medicineViewmodel, navController = navController)
        }
        composable<Routes.MedicineCrud> {
            MedicineCrud(medicineViewmodel = medicineViewmodel, navController = navController)
        }


        // doctor
        composable<Routes.DoctorHomePage> {
            DoctorHomepageScreen(navController, mainViewModel)
        }
        composable<Routes.ManageScheduleScreen> {
            ManageScheduleScreen()
        }
        composable<Routes.ManageAppointmentScreen> {
            ManageAppointmentScreen(DatabaseViewModel())
        }

        composable<Routes.AiScreen> {
            AiScreen(aiViewmodel)
        }


        // nav bars

        composable<Routes.Accounts> {
            AccountScreen(authViewmodel = authViewmodel, navController = navController)
        }

        composable<Routes.ReportBug> {
            ReportBugScreen(
                authViewmodel = authViewmodel, navController = navController
            )
        }
        composable<Routes.Setting> {
            SettingsScreen(
                authViewmodel = authViewmodel, navController = navController
            )
        }
        composable<Routes.Developers> {
            DevelopersScreen(
                navController = navController
            )
        }
        composable<Routes.Security> {
            SecurityScreen(
                authViewmodel = authViewmodel, navController = navController
            )
        }
    }
}




