package com.example.medai.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.db.volkorn
import com.example.medai.util.Routes


@Composable
fun NewsDesign(
    newsItem: Routes.NewsItem, onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 10.dp, vertical = 8.dp
            ),
        shape = RoundedCornerShape(6.dp),
        onClick = {
            onClick()
        },
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = newsItem.title,
                    fontFamily = volkorn,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp
                )
                Text(
                    text = "by- ${newsItem.description}",
                    fontFamily = volkorn,
                    fontSize = 13.sp
                )
            }
            Text(
                text = newsItem.timestamp.toString(),
                fontFamily = volkorn,
                maxLines = 1,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 3.dp, end = 5.dp),
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun GFfh() {
    NewsDesign(
        newsItem = Routes.NewsItem(
            title = "new 1",
            description = "des 1",
            author = "auth 1",
            timestamp = 102155455
        )
    ) {

    }
}