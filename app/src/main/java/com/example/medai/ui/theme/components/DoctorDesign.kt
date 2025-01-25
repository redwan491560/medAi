package com.example.medai.ui.theme.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.db.volkorn



@Composable
fun DoctorDesign(name: String, group: String, visit: String, image: Int) {
    OutlinedCard(
        shape = RoundedCornerShape(6.dp),
        onClick = {

        },
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(0.dp, Color.Transparent)
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                horizontalAlignment = Alignment.Start, modifier = Modifier.padding(8.dp)
            ) {
                Text(text = name, fontFamily = volkorn, fontSize = 18.sp)
                Text(text = "($group)", fontFamily = volkorn, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Fees: $visit tk", fontFamily = volkorn, fontSize = 18.sp)
            }
        }

    }
}