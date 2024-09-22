package com.example.medai.user

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medai.R
import com.example.medai.db.Screens
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.DropdownCard
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.NavigationBarIcon
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.viewmodels.MainViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookAppointment(navController: NavHostController, mainViewModel: MainViewModel) {

    var context = LocalContext.current
    var search by remember {
        mutableStateOf("")
    }
    var chipState by remember {
        mutableIntStateOf(0)
    }
    val chipsDocs = listOf("Online", "Onsite")

    var presentState by remember {
        mutableStateOf(true)
    }

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
                verticalArrangement = Arrangement.spacedBy(10.dp)
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
        // screen starts from here
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
                            text = "Book Appointment",
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontFamily = volkorn
                        )
                    }
                    Row(
                        modifier = Modifier.weight(4f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconsDesign(
                            src = painterResource(id = R.drawable.search_doctors),
                            size = 25
                        ) {
                            // search doctors by name

                        }
                        IconsDesign(src = painterResource(id = R.drawable.home)) {
                            navController.navigate(Screens.MainScreen.name)
                        }
                        IconsDesign(src = painterResource(id = R.drawable.menus)) {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                // chips row
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 5.dp),
                ) {
                    chipsDocs.forEachIndexed { index, string ->
                        Text(text = string,
                            fontFamily = volkorn,
                            fontSize = 20.sp,
                            color = if (chipState == index) Color.Blue
                            else Color.Black,
                            textDecoration = if (chipState == index) TextDecoration.Underline
                            else TextDecoration.None,
                            modifier = Modifier.clickable {
                                chipState = index
                            })
                    }

                }

                // location, hospital name, department, doctors name, date
                // after selecting all the feeds it will show a serial number and when the doctor starts his chamber

                Text(
                    text = "Select All the fields to find Doctors",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp),
                    textAlign = TextAlign.End,
                    fontFamily = volkorn,
                    color = Color.Red
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    DropdownCard(
                        itemsList = mainViewModel.locationList,
                        info = mainViewModel.location
                    )
                    Spacer(modifier = Modifier.width(15.dp))

                    DropdownCard(
                        itemsList = mainViewModel.hospitalList,
                        info = mainViewModel.hospital
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    DropdownCard(
                        itemsList = mainViewModel.departmentList,
                        info = mainViewModel.department
                    )
                    Spacer(modifier = Modifier.width(15.dp))

                    DropdownCard(
                        itemsList = mainViewModel.timeList,
                        info = mainViewModel.time
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    DropdownCard(
                        itemsList = mainViewModel.doctorsList,
                        info = mainViewModel.doctor
                    )
                }

                //  Result Showing Column
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        shape = RoundedCornerShape(4.dp),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    TextDesign(name = "(${mainViewModel.department.value})", font = 20)
                                    Spacer(modifier = Modifier.height(5.dp))
                                    TextDesign(name = mainViewModel.doctor.value, font = 24)
                                    TextDesign(name = "- MBBS, BCS (Health)", font = 14)
                                    TextDesign(name = "- FCPS", font = 14)
                                }
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.tanvir_mohit),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(180.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )

                                }
                            }
                            TextDesign(name = "Assistant Professor, (Medicine)")
                            TextDesign(name = "Sylhet Osmani Medical College and Hospital")
                        }
                    }
                    // end of result
                    OutlinedCard(
                        border = BorderStroke(1.dp, color = Color.Black),
                        modifier = Modifier
                            .padding(15.dp)
                            .align(Alignment.End),
                        shape = RoundedCornerShape(6.dp),
                        elevation = CardDefaults.cardElevation(0.dp),

                        ) {
                        Text(
                            text = "Book Appointment",
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    scope.launch {
                                        Toast
                                            .makeText(
                                                context,
                                                "Appointment booking successful",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }

                                },
                            fontFamily = volkorn,
                            textDecoration = TextDecoration.Underline
                        )
                    }
                }



            }

        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun Adfdddj() {
    BookAppointment(navController = rememberNavController(), mainViewModel = MainViewModel())
}