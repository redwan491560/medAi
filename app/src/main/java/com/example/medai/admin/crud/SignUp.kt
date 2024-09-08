package com.example.medai.admin.crud

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medai.viewmodels.MainViewModel
import com.example.medai.R
import com.example.medai.db.Screens
import com.example.medai.ui.theme.signup
import com.example.medai.ui.theme.textFieldColor
import com.example.medai.ui.theme.textFieldColor2
import com.example.medai.db.volkorn

@Composable
fun SignUp(navController: NavHostController, mainViewModel: MainViewModel) {

    var handle by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var retypePass by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }
    var checkedState by remember {
        mutableStateOf(false)
    }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.signup),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            contentScale = ContentScale.Crop,

            )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .alpha(0.6f)
                .background(signup)
        )

        Column(
            Modifier
                .fillMaxWidth()
                .padding(50.dp, 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp),
                    colorFilter = ColorFilter.tint(Color.Red)

                )
                Text(text = "MedAi", fontSize = 24.sp, fontFamily = volkorn, color = Color.White)
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TextField(
                    value = handle,
                    onValueChange = {
                        handle = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontFamily = volkorn, fontSize = 14.sp
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            color = Color.Black,
                            text = "Handle name",
                            fontFamily = volkorn,
                            fontSize = 14.sp,
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(0.dp, 5.dp),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color(textFieldColor2.value),
                        unfocusedContainerColor = Color(textFieldColor.value)
                    )
                )
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontFamily = volkorn, fontSize = 14.sp
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            color = Color.Black,
                            text = "Email",
                            fontFamily = volkorn,
                            fontSize = 14.sp,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(0.dp, 5.dp),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color(textFieldColor2.value),
                        unfocusedContainerColor = Color(textFieldColor.value)
                    )
                )
                TextField(
                    value = phone,
                    onValueChange = {
                        phone = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontFamily = volkorn, fontSize = 14.sp
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            color = Color.Black,
                            text = "Contact",
                            fontFamily = volkorn,
                            fontSize = 14.sp,
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(0.dp, 5.dp),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            // validate phone
                        }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color(textFieldColor2.value),
                        unfocusedContainerColor = Color(textFieldColor.value)
                    )
                )
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontFamily = volkorn, fontSize = 14.sp
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            color = Color.Black,
                            text = "Password",
                            fontFamily = volkorn,
                            fontSize = 14.sp,
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(0.dp, 5.dp),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color(textFieldColor2.value),
                        unfocusedContainerColor = Color(textFieldColor.value)
                    )
                )
                TextField(
                    value = retypePass,
                    onValueChange = {
                        retypePass = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontFamily = volkorn, fontSize = 14.sp
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            color = Color.Black,
                            text = "Retype password",
                            fontFamily = volkorn,
                            fontSize = 14.sp,
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(0.dp, 5.dp),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            // validate the user
                        }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color(textFieldColor2.value),
                        unfocusedContainerColor = Color(textFieldColor.value)
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Checkbox(checked = checkedState, onCheckedChange = {
                        checkedState = !checkedState
                    })
                    Text(
                        text = "Agree to the terms and condition",
                        fontFamily = volkorn,
                        fontSize = 12.sp
                    )
                }
                OutlinedButton(
                    onClick = {
                        navController.navigate(Screens.MainScreen.name)
                    },
                    colors = ButtonDefaults.buttonColors(signup),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Sign Up", color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = volkorn,
                    )

                }

            }
        }


    }

}

@Preview(showSystemUi = true)
@Composable
private fun dcghdddg() {
    SignUp(rememberNavController(), MainViewModel())
}