package com.example.medai.user_owner

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
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.NavigationBarIcon
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.ui.theme.textFieldColor
import com.example.medai.ui.theme.textFieldColor2
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OwnerHomePage(navController: NavHostController) {

    val viewmodel = StoreViewmodel()
    var status = viewmodel.getStatus().value

    var search by remember {
        mutableStateOf("")
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
        // content column


        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .systemBarsPadding()
                .padding(5.dp)
        ) {
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
                                text = "Search",
                                fontFamily = volkorn,
                                fontSize = 12.sp,
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .weight(8f)
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

                    Row(
                        modifier = Modifier.weight(4f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {


                        Image(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = null, modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = null, modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = null, modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                        )

                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp)
                        .height(60.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // crud pharmacy

                    Image(
                        painter = painterResource(id = R.drawable.db_write),
                        contentDescription = null, modifier = Modifier.clickable {

                        }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.update_db),
                        contentDescription = null, modifier = Modifier.clickable {

                        }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.delete_db),
                        contentDescription = null, modifier = Modifier.clickable {

                        }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.read_db),
                        contentDescription = null, modifier = Modifier.clickable {

                        }
                    )


                }

                Row {
                    TextDesign(name = "Modify database")
                }
                Column(
                    modifier = Modifier.padding(20.dp, 5.dp)
                ) {
                    TextDesign(name = "Medicine", modifier = Modifier.clickable {

                    })
                    TextDesign(name = "Products", modifier = Modifier.clickable {

                    })
                }

            }

        }


    }


}

@Preview(showSystemUi = true)
@Composable
private fun SHdyf() {
    OwnerHomePage(rememberNavController())
}