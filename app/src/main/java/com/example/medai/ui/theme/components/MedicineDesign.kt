package com.example.medai.ui.theme.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ivory

@Composable
fun MedicineDesign(
    name: String, price: Int, usage: String, group: String, onClick: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        border = BorderStroke(1.dp, Color.Blue),
        colors = CardDefaults.cardColors(ivory),
        shape = RoundedCornerShape(6.dp),
        onClick = {
            onClick()
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(9f)
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = name,
                        fontSize = 20.sp,
                        fontFamily = volkorn,
                        maxLines = 2,
                        modifier = Modifier.weight(5f)
                    )
                    Text(
                        text = "tk $price",
                        fontSize = 18.sp,
                        color = Color.Blue,
                        fontFamily = volkorn, maxLines = 1,
                        modifier = Modifier.weight(4f)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = usage,
                        modifier = Modifier.weight(5f),
                        fontSize = 14.sp, maxLines = 2,
                        fontFamily = volkorn
                    )
                    Text(
                        text = group,
                        fontSize = 13.sp,
                        fontFamily = volkorn,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.weight(4f)
                    )

                }

            }
            Image(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(40.dp)
                    .clickable { onClick() })
        }

    }
}


@Preview(showSystemUi = true)
@Composable
private fun DHdgt() {
    MedicineDesign(
        name = "SecSecloSecloSeclolo",
        usage = "used for gastric",
        price = 1200,
        group = "Omeprazole"
    ) {

    }
}