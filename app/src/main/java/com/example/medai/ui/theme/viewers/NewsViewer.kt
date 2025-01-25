package com.example.medai.ui.theme.viewers

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.medai.R
import com.example.medai.db.NewsItem
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.viewmodels.item.NewsViewmodel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsViewer(news: NewsItem, newsViewmodel: NewsViewmodel) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    var chipsState by remember { mutableIntStateOf(0) }
    val chipsMain = listOf("Articles", "News")

    ModalNavigationDrawer(drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.padding(5.dp),
                drawerTonalElevation = DrawerDefaults.ModalDrawerElevation,
                drawerShape = RoundedCornerShape(10.dp),
                drawerContainerColor = Color(drawerColor.value)
            ) {
                Column(
                    modifier = Modifier
                        .size(300.dp)
                        .padding(15.dp, 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextDesign(name = "Settings")
                    TextDesign(name = "Security")
                    TextDesign(name = "Developer options")
                    TextDesign(name = "Routine checks")
                }
            }
        }) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Category", fontFamily = volkorn, fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .background(Color.Red)
                            .padding(15.dp, 3.dp)
                            .clip(RoundedCornerShape(0.dp, 3.dp, 6.dp, 0.dp))
                    )
                    Row(
                        modifier = Modifier.padding(15.dp),
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        IconsDesign(src = painterResource(id = R.drawable.cart)) {

                        }
                        IconsDesign(src = painterResource(id = R.drawable.notification)) {

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

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                ) {
                    Text(
                        text = news.title,
                        fontSize = 20.sp,
                        fontFamily = volkorn,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Text(
                        text = news.author,
                        fontSize = 14.sp,
                        fontFamily = volkorn,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = news.description,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Justify,
                            fontFamily = volkorn,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(15.dp))

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {

                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            chipsMain.forEachIndexed { index, string ->
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
                                        })
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
private fun DefaultDatabaseErr() {
}