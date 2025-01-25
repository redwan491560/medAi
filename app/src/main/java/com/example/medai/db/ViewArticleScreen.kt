package com.example.medai.db

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medai.ui.theme.components.ArticleDesign
import com.example.medai.util.Routes
import com.example.medai.viewmodels.item.ArticleViewmodel

@Composable
fun ViewArticleScreen(articleViewmodel: ArticleViewmodel, navController: NavHostController) {

    LaunchedEffect(Unit) { articleViewmodel.readAllArticles() }
    val articleList = articleViewmodel.articleList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        LazyColumn {
            items(articleList.value) { item ->
                val timeStamp = articleViewmodel.convertTimestampToDate(item.timeStamp)
                ArticleDesign(item, timeStamp = timeStamp) {
                    navController.navigate(
                        Routes.ArticleViewer(
                            title = item.title,
                            description = item.description,
                            author = item.author,
                            timeStamp = item.timeStamp,
                            category = "",
                            view = 0,
                            image = 0,
                            rating = 0.2,
                            story = item.story
                        )
                    )
                }
            }
        }
    }
}


