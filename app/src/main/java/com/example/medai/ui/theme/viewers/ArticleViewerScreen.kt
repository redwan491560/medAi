package com.example.medai.ui.theme.viewers

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.R
import com.example.medai.db.volkorn


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArticleViewerScreen(
    title: String,
    uid: String,
    description: String,
    author: String,
    story: String
) {


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
            .systemBarsPadding()
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp, 5.dp),
        ) {

            Text(
                text = title,
                fontSize = 18.sp,
                fontFamily = volkorn,
                maxLines = 1, overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
            )

            Text(
                text = "by- $author",
                fontSize = 16.sp,
                fontFamily = volkorn,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(15.dp))
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.demo),
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
                    text = description,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    fontFamily = volkorn,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = story,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    fontFamily = volkorn,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

