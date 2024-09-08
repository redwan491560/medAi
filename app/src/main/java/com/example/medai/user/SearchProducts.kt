package com.example.medai.user

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.ui.theme.textFieldColor
import com.example.medai.ui.theme.textFieldColor2
import com.example.medai.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchProducts(navController: NavHostController, mainViewModel: MainViewModel) {

    var search by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true,
        drawerContent = {
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
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    BarTextDesign(name = "Settings") {}
                    BarTextDesign(name = "Personalize") {}
                    BarTextDesign(name = "Security") {}
                    BarTextDesign(name = "Developer options") {}
                    BarTextDesign(name = "Routine checks") {}
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
                                src = painterResource(id = R.drawable.app_logo),
                                size = 50
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
                            text = "Search Products",
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
                            src = painterResource(id = R.drawable.cart)
                        ) {
                            navController.navigate(Screens.MainScreen.name)

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
                // show contents
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
                        // perform fts
                    }),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black,
                        focusedContainerColor = Color(textFieldColor2.value),
                        unfocusedContainerColor = Color(textFieldColor.value)
                    )
                )
            }

        }

    }
}

@Preview
@Composable
private fun Afssdf() {
    SearchProducts(rememberNavController(), MainViewModel())
}