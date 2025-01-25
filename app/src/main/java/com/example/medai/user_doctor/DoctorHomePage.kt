package com.example.medai.user_doctor

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.medai.R
import com.example.medai.db.listOfMeds
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.NavigationBarIcon
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.components.MedicineDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.util.Routes
import com.example.medai.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DoctorHomepageScreen(navController: NavHostController, viewModel: MainViewModel) {

    val chips = listOf("Article", "News", "Notice")
    var chipsState by remember { mutableIntStateOf(0) }

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
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
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                NavigationBarIcon(
                    title = "Profile", icon = painterResource(id = R.drawable.profile)
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
        // content

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .systemBarsPadding()
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(7f)
                            .padding(10.dp, 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Home Page",
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontFamily = volkorn
                        )
                    }
                    Row(
                        modifier = Modifier
                            .weight(4f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly

                    ) {
                        IconsDesign(src = painterResource(id = R.drawable.cart)) {
                            //navController.navigate(Screens.CartScreen.name)
                            // navigate to cart screen
                            // cart screen is a type of local database that stores the data into local database
                            // once the order is placed then the data is sent to admin to place the order

                        }
                        IconsDesign(src = painterResource(id = R.drawable.notification)) {
                            // to notification screen
                        }
                        AsyncImage(
                            model = "https://cdn-icons-png.flaticon.com/128/17688/17688367.png",
                            contentDescription = null,
                            modifier = Modifier
                                .size(35.dp)
                                .clickable {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                        )
                    }
                }

                // content row

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconsDesign(
                        src = painterResource(id = R.drawable.appointment), size = 90
                    ) {
                        navController.navigate(Routes.ManageAppointmentScreen)
                    }
                    IconsDesign(
                        src = painterResource(id = R.drawable.schedule), size = 90
                    ) {
                        navController.navigate(Routes.ManageScheduleScreen)
                    }
                    IconsDesign(
                        src = painterResource(id = R.drawable.article), size = 90
                    ) {
                        navController.navigate(Routes.UploadArticleScreen)
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(13.dp, 5.dp)
                ) {
                    chips.forEachIndexed { index, string ->
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
                    Image(
                        painter = painterResource(id = R.drawable.sort),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(20.dp)
                            .padding(10.dp, 0.dp)
                            .clickable {
                                if (chipsState == 0) {
                                    // edit an schedule
                                } else {
                                    // edit an appointment
                                }
                            },
                        alignment = Alignment.CenterEnd
                    )
                }

                // article or news showing column
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    when (chipsState) {
                        1 -> {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                listOfMeds.forEach {
                                    MedicineDesign(
                                        name = it.name,
                                        price = it.price,
                                        group = it.group,
                                        usage = it.usage
                                    ) {

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
private fun ASfdg() {
    DoctorHomepageScreen(rememberNavController(), MainViewModel())
}