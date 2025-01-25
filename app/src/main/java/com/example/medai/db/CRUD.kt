package com.example.medai.db

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medai.R
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.util.Routes
import com.example.medai.viewmodels.AuthViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DbCRUD(
    authViewmodel: AuthViewModel,
    navController: NavHostController
) {




    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            navController.popBackStack()
                        })
                TextDesign(name = "Maintain Database")
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(vertical = 60.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            Card(
                colors = CardDefaults.cardColors(Color(0xFFA3FAF6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.clickable {
                    navController.navigate(
                        Routes.MedicineCrud
                    )
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.server),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    TextDesign(name = "Medicine Database")
                }
            }

            Card(
                colors = CardDefaults.cardColors(Color(0xFFA3FAF6)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.clickable {
                    navController.navigate(
                        Routes.ArticleCrud
                    )
                }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.server),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    TextDesign(name = "Article Database")
                }
            }

            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.clickable {
                    //
                },
                colors = CardDefaults.cardColors(Color(0xFFA3FAF6)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.server),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    TextDesign(name = "News Database")
                }
            }

            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.clickable {
                    //
                },
                colors = CardDefaults.cardColors(Color(0xFFA3FAF6)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.server),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    TextDesign(name = "Doctor Database")
                }
            }

        }
    }
}
