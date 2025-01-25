package com.example.medai.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.R
import com.example.medai.db.volkorn
import com.example.medai.util.Routes

@Composable
fun ArticleDesign(
    article: Routes.ArticleViewer, timeStamp: String = "", onClick: () -> Unit
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
            Row(
                modifier = Modifier.padding(start = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.security),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column(
                    Modifier
                        .padding(8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = article.title,
                        fontFamily = volkorn,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 18.sp
                    )
                    Text(
                        text = article.description,
                        fontFamily = volkorn,
                        fontSize = 14.sp,
                        maxLines = 3
                    )
                    Text(
                        text = "by- ${article.author}",
                        fontFamily = volkorn,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
            Text(
                text = "Date: $timeStamp",
                fontFamily = volkorn,
                maxLines = 1,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 3.dp, end = 5.dp),
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp
            )

        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun GFfh() {
    ArticleDesign(
        article = Routes.ArticleViewer(
            description = "description",
            title = "title",
            author = "author 2",
            timeStamp = 102454523,
            category = "sports"

        )
    ) {

    }
}