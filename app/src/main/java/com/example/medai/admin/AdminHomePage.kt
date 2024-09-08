package com.example.medai.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.viewmodels.MainViewModel
import com.example.medai.R
import com.example.medai.db.volkorn


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminHomePage(navController: NavHostController, mainViewModel: MainViewModel) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 10.dp), topBar = {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            IconsDesign(
                src = painterResource(id = R.drawable.home), size = 36
            ) {

            }
            IconsDesign(
                src = painterResource(id = R.drawable.server), size = 30
            ) {

            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(0.dp, 6.dp), horizontalArrangement = Arrangement.End
        ) {
            Card(
                shape = RoundedCornerShape(4.dp, 0.dp, 0.dp, 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Red, contentColor = Color.Black
                ),
            ) {
                TextDesign(
                    name = "Administrator", font = 14, modifier = Modifier.padding(8.dp, 0.dp)
                )
            }
        }
    }, floatingActionButton = {
        Card(
            shape = RoundedCornerShape(4.dp)
        ) {
            IconsDesign(
                src = painterResource(id = R.drawable.change), modifier = Modifier.padding(8.dp)
            ) {

            }
        }

    }) {

        Column(
            Modifier.padding(10.dp, 80.dp, 0.dp, 10.dp)
        ) {
            Text(
                text = "Modify databases",
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                fontFamily = volkorn,
                modifier = Modifier.padding(5.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Medicine Table",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {

                    },
                fontFamily = volkorn
            )
            Text(
                text = "Article Table", fontSize = 20.sp,

                modifier = Modifier
                    .padding(5.dp)
                    .clickable {

                    }, fontFamily = volkorn
            )
            Text(
                text = "Doctors Table",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {

                    },
                fontFamily = volkorn
            )
            Text(
                text = "Affiliated hospitals",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {

                    },
                fontFamily = volkorn
            )

        }

    }

}


@Preview(
    showSystemUi = true
)
@Composable
private fun Affxc() {
    AdminHomePage(rememberNavController(), MainViewModel())
}