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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.medai.R
import com.example.medai.db.Screens
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.BarTextDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.viewmodels.ClickableStateViewModel
import com.example.medai.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookLabTest(navController: NavHostController, mainViewModel: MainViewModel) {

    val state = ClickableStateViewModel()
    var chipsState by remember { mutableIntStateOf(0) }
    val chips = listOf("Remote test", "Onsite test")

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier.padding(5.dp),
            drawerTonalElevation = DrawerDefaults.ModalDrawerElevation,
            drawerShape = RoundedCornerShape(10.dp),
            drawerContainerColor = Color(drawerColor.value)
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight()
                    .padding(15.dp, 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                BarTextDesign(name = "Settings") {}
                BarTextDesign(name = "Personalize") {}
                BarTextDesign(name = "Security") {}
                BarTextDesign(name = "Developer options") {}
                BarTextDesign(name = "Routine checks") {}
            }
        }
    }) {
        Scaffold(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            Column(
                Modifier.padding(8.dp)
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
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Book Labtest",
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
                        IconsDesign(src = painterResource(id = R.drawable.cart)) {
                            navController.navigate(Screens.CartScreen.name)

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
                Column(
                    Modifier.fillMaxSize()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(30.dp),
                        modifier = Modifier.padding(5.dp, 0.dp)
                    ) {
                        chips.forEachIndexed { index, string ->
                            Text(text = string,
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = if (chipsState == index) Color.Blue
                                else Color.Black,
                                textDecoration = if (chipsState == index) TextDecoration.Underline
                                else TextDecoration.None,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .clickable {
                                        chipsState = index
                                    }
                            )
                        }
                    }


                }
            }

        }
    }

}

@Preview
@Composable
fun Afsf() {
    BookLabTest(rememberNavController(), MainViewModel())
}