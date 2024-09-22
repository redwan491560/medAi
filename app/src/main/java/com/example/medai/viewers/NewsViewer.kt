package com.example.medai.viewers

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.R
import com.example.medai.db.News
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.ArticleDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.viewmodels.DatabaseViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsViewer(news: News, viewModel: DatabaseViewModel) {
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
                        IconsDesign(src = painterResource(id = R.drawable.menus)) {
                            scope.launch {
                                drawerState.open()
                            }
                        }
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
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = news.image!!),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(300.dp, 180.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Crop
                            )
                        }
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
                            Image(
                                painter = painterResource(id = R.drawable.advertisement),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp)),
                                alignment = Alignment.Center
                            )
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
                        Column(
                            Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            when (chipsState) {
                                1 -> {
                                    viewModel.newsList.forEach {
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

                                else -> {
                                    viewModel.articleList.forEach {
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
}

@Preview(showSystemUi = true)
@Composable
private fun DefaultDatabaseErr() {
    NewsViewer(
        news = News(
            title = "19 infected in covid-19",
            description = "19 infected in covid-19 19 infected in covid-19 19 infected in covid-19 19 infected in covid-19 19 infected in covid 19 infeccovid-19",
            author = "somebody",
            image = R.drawable.demo,
            view = 1025,
            rating = 3.4
        ), viewModel = DatabaseViewModel()
    )
}