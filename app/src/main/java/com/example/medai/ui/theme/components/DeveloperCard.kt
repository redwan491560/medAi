package com.example.medai.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign


@Composable
fun DevOpsCard(
    name: String,
    email: String,
    post: String,
    image: Painter?,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.padding(horizontal = 50.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextDesign(name = name, font = 20)
                TextDesign(
                    name = email,
                    font = 16,
                )
                TextDesign(name = post, font = 16)
            }
        }
        Image(
            painter = image!!,
            contentDescription = null,
            modifier = modifier.size(80.dp)
        )
        Text(
            text = "Visit",
            fontFamily = volkorn,
            fontSize = 14.sp,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .align(
                    Alignment.TopEnd
                )
                .padding(top = 33.dp, end = 8.dp)
        )
    }
}