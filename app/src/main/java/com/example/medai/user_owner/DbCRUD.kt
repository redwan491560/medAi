package com.example.medai.user_owner

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medai.R
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign
import com.example.medai.ui.theme.signup
import com.example.medai.ui.theme.signupCard
import com.example.medai.ui.theme.textFieldColor
import com.example.medai.ui.theme.textFieldColor2
import com.example.medai.viewmodels.DatabaseViewModel
//
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun DbCRUD(viewModel: DatabaseViewModel, navHostController: NavHostController) {
//
//
//    var search by remember {
//        mutableStateOf("")
//    }
//
//    Scaffold(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Column(
//            Modifier.fillMaxSize()
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(20.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                TextField(
//                    value = search,
//                    onValueChange = {
//                        search = it
//                    },
//                    textStyle = TextStyle(
//                        color = Color.Black, fontFamily = volkorn, fontSize = 16.sp
//                    ),
//                    singleLine = true,
//                    placeholder = {
//                        Text(
//                            color = Color.Black,
//                            text = "Search",
//                            fontFamily = volkorn,
//                            fontSize = 12.sp,
//                        )
//                    },
//                    modifier = Modifier
//                        .height(60.dp)
//                        .padding(0.dp, 5.dp),
//                    shape = RoundedCornerShape(6.dp),
//                    keyboardActions = KeyboardActions(onDone = {
//                        // performs an fts and shows data on a dropdown menu box
//                    }),
//                    colors = TextFieldDefaults.colors(
//                        focusedTextColor = Color.Black,
//                        unfocusedTextColor = Color.Black,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        focusedContainerColor = Color(textFieldColor2.value),
//                        unfocusedContainerColor = Color(textFieldColor.value)
//                    )
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.server),
//                    contentDescription = null,
//                    modifier = Modifier.size(30.dp)
//                )
//                Image(
//                    painter = painterResource(id = R.drawable.settings),
//                    contentDescription = null,
//                    modifier = Modifier.size(35.dp)
//                )
//            }
//            ComposablesDesign.TextDesign(name = "Database name")
//            Spacer(modifier = Modifier.height(30.dp))
//            TextField(
//                value = viewModel.userName.value,
//                onValueChange = {
//                    viewModel.userName.value = it
//                },
//                textStyle = TextStyle(
//                    fontFamily = volkorn,
//                    fontSize = 14.sp
//                ),
//                singleLine = true,
//                placeholder = {
//                    Text(
//                        text = "Username",
//                        fontFamily = volkorn,
//                        fontSize = 14.sp,
//                    )
//
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(60.dp)
//                    .padding(0.dp, 5.dp)
//                    .clip(RoundedCornerShape(10.dp)),
//                shape = RoundedCornerShape(6.dp),
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Text,
//                    imeAction = ImeAction.Next
//                ),
//                colors = TextFieldDefaults.colors(
//                    focusedTextColor = Color.White,
//                    unfocusedTextColor = Color.Black,
//                    focusedPlaceholderColor = Color.White,
//                    unfocusedPlaceholderColor = Color.Black,
//                    focusedIndicatorColor = Color.Blue,
//                    unfocusedIndicatorColor = Color.Black,
//                    focusedContainerColor = Color(signup.value),
//                    unfocusedContainerColor = Color(signupCard.value)
//                )
//            )
//
//
//        }
//    }
//
//}
//
