package com.example.medai.user

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medai.R
import com.example.medai.db.Screens
import com.example.medai.db.articleList
import com.example.medai.db.listOfDoctors
import com.example.medai.db.listOfMeds
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.ArticleDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.DoctorDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.MedicineDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.NavigationBarIcon
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.ui.theme.textFieldColor
import com.example.medai.ui.theme.textFieldColor2
import com.example.medai.viewmodels.MainViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {

    val context = LocalContext.current

    var search by remember {
        mutableStateOf("")
    }
    var chipsState by remember { mutableIntStateOf(0) }
    val chipsMain = listOf("Articles", "Medicine", "Doctors")

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val loadDataOnCreate = rememberCoroutineScope()
    // load all the necessary data upon the opening of the app

    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .padding(10.dp, 5.dp),
            drawerTonalElevation = DrawerDefaults.ModalDrawerElevation,
            drawerContainerColor = Color(drawerColor.value),
            drawerShape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp, 5.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                NavigationBarIcon(
                    title = "Questions and Answers", icon = painterResource(id = R.drawable.qna)
                ) {

                }
                NavigationBarIcon(
                    title = "Medical history",
                    icon = painterResource(id = R.drawable.medical_history)
                ) {

                }
                NavigationBarIcon(
                    title = "Check ICU",
                    icon = painterResource(id = R.drawable.medical_history)
                ) {

                }
                NavigationBarIcon(
                    title = "Settings", icon = painterResource(id = R.drawable.settings)
                ) {

                }
                NavigationBarIcon(
                    title = "Find Blood", icon = painterResource(id = R.drawable.find_blood)
                ) {

                }
                NavigationBarIcon(
                    title = "Donate Blood", icon = painterResource(id = R.drawable.donate_blood)
                ) {

                }
                NavigationBarIcon(
                    title = "Personalize", icon = painterResource(id = R.drawable.personalize)
                ) {

                }
                NavigationBarIcon(
                    title = "Routine check up",
                    icon = painterResource(id = R.drawable.routine_checkup)
                ) {

                }

                NavigationBarIcon(
                    title = "Security", icon = painterResource(id = R.drawable.security)
                ) {

                }
                NavigationBarIcon(
                    title = "Developer options", icon = painterResource(id = R.drawable.dev_ops)
                ) {

                }
                NavigationBarIcon(
                    title = "Report bug", icon = painterResource(id = R.drawable.report_bug)
                ) {

                }

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Row(
                        Modifier.padding(10.dp, 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconsDesign(
                            src = painterResource(id = R.drawable.app_logo), size = 50
                        ) {

                        }
                        TextDesign(name = "MedAi", font = 20)
                    }
                }
            }
        }
    }) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .systemBarsPadding()
                .padding(5.dp),
            floatingActionButton = {
                Card(
                    shape = RoundedCornerShape(6.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    IconsDesign(
                        modifier = Modifier.padding(5.dp),
                        src = painterResource(id = R.drawable.app_logo),
                        size = 35
                    ) {

                    }
                }
            }
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(7f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // search bar text field
                        // searches medicine and products all sort of
                        TextField(
                            value = search,
                            onValueChange = {
                                search = it
                            },
                            textStyle = TextStyle(
                                color = Color.Black, fontFamily = volkorn, fontSize = 16.sp
                            ),
                            singleLine = true,
                            placeholder = {
                                Text(
                                    color = Color.Black,
                                    text = "Search medicine",
                                    fontFamily = volkorn,
                                    fontSize = 12.sp,
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .padding(0.dp, 5.dp),
                            shape = RoundedCornerShape(6.dp),
                            keyboardActions = KeyboardActions(onDone = {
                                // performs an fts and shows data on a dropdown menu box
                            }),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedContainerColor = Color(textFieldColor2.value),
                                unfocusedContainerColor = Color(textFieldColor.value)
                            )
                        )
                    }
                    Row(
                        modifier = Modifier.weight(4f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly

                    ) {
                        IconsDesign(src = painterResource(id = R.drawable.cart)) {
                            navController.navigate(Screens.CartScreen.name)
                            // navigate to cart screen
                            // cart screen is a type of local database that stores the data into local database
                            // once the order is placed then the data is sent to admin to place the order

                        }
                        IconsDesign(src = painterResource(id = R.drawable.notification)) {
                            // to notification screen
                        }
                        IconsDesign(src = painterResource(id = R.drawable.menus)) {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconsDesign(
                        src = painterResource(id = R.drawable.bookapp), size = 90
                    ) {
                        navController.navigate(Screens.BookAppointmentScreen.name)
                    }
                    IconsDesign(
                        src = painterResource(id = R.drawable.booktest), size = 90
                    ) {
                        navController.navigate(Screens.BookLabTestScreen.name)
                    }
                    IconsDesign(
                        src = painterResource(id = R.drawable.searchprod), size = 90
                    ) {
                        navController.navigate(Screens.SearchProductsScreen.name)
                    }
                    IconsDesign(
                        src = painterResource(id = R.drawable.record), size = 90
                    ) {
                        navController.navigate(Screens.RecordActivitiesScreen.name)
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        chipsMain.forEachIndexed { index, string ->
                            Text(text = string,
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = if (chipsState == index) {
                                    Color.Blue
                                } else {
                                    Color.Black
                                },
                                textDecoration = if (chipsState == index) {
                                    TextDecoration.Underline
                                } else {
                                    TextDecoration.None
                                },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .clickable {
                                        chipsState = index
                                    })
                        }
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        when (chipsState) {
                            2 -> {
                                listOfDoctors.forEach {
                                    DoctorDesign(
                                        name = it.name,
                                        group = it.group,
                                        visit = it.visit,
                                        image = it.image
                                    )
                                }
                            }

                            1 -> {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    listOfMeds.forEach {
                                        MedicineDesign(
                                            name = it.name,
                                            usage = it.usage,
                                            price = it.price,
                                            group = it.group
                                        )
                                    }
                                }
                            }

                            else -> {
                                articleList.forEach {
                                    ArticleDesign(
                                        title = it.title,
                                        author = it.author,
                                        views = it.view,
                                        rating = it.rating.toString()
                                    ) {
                                        // figure out how to navigate to this composable and take in data as parameter
                                        // ArticleViewerScreen(it)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun Aps() {
    MainScreen(navController = rememberNavController(), mainViewModel = MainViewModel())
}