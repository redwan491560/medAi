package com.example.medai.admin.crud

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medai.R
import com.example.medai.db.Status
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.login
import com.example.medai.ui.theme.loginCard
import com.example.medai.ui.theme.textFieldColor2
import com.example.medai.util.Routes
import com.example.medai.viewmodels.AuthViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Login(navController: NavHostController, authViewmodel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current

    val authState = authViewmodel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is Status.Error -> Toast.makeText(
                context,
                (authState.value as Status.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            is Status.Authenticated -> navController.navigate(Routes.Main)

            else -> Unit
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .alpha(0.7f)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(login)
            )

            // contents
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(15.dp)
                            .size(60.dp),
                        colorFilter = ColorFilter.tint(Color.Black)

                    )
                    Text(
                        text = "MedAi",
                        fontSize = 24.sp,
                        fontFamily = volkorn,
                        color = Color.Black
                    )

                }
                Column(
                    modifier = Modifier
                        .padding(0.dp, 20.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        textStyle = TextStyle(
                            fontFamily = volkorn,
                            fontSize = 14.sp
                        ),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Email",
                                fontFamily = volkorn,
                                fontSize = 14.sp,
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(0.dp, 5.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(6.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedPlaceholderColor = Color.Black,
                            unfocusedPlaceholderColor = Color.Black,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color(login.value),
                            unfocusedContainerColor = Color(loginCard.value)
                        )
                    )

                    TextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        textStyle = TextStyle(
                            fontFamily = volkorn,
                            fontSize = 14.sp
                        ),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        placeholder = {
                            Text(
                                text = "Password",
                                fontFamily = volkorn,
                                fontSize = 14.sp,
                            )

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(0.dp, 5.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        shape = RoundedCornerShape(6.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedPlaceholderColor = Color.Black,
                            unfocusedPlaceholderColor = Color.Black,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color(login.value),
                            unfocusedContainerColor = Color(loginCard.value)
                        )
                    )
                }
                if (authState.value == Status.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        colors = ButtonDefaults.buttonColors(textFieldColor2),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {
                            authViewmodel.logIn(email, password)
                            when (authState.value) {
                                is Status.Error -> {
                                    Toast.makeText(
                                        context,
                                        (authState.value as Status.Error).message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    authViewmodel.emptyState()
                                }

                                else -> Unit
                            }
                        }
                    ) {
                        Text(
                            text = "Login", color = Color.Black,
                            fontSize = 16.sp,
                            fontFamily = volkorn
                        )
                    }
                }

            }


            Spacer(
                modifier = Modifier
                    .width(600.dp)
                    .height(450.dp)
                    .offset(y = 310.dp)
                    .scale(2f, 1f)
                    .clip(RoundedCornerShape(200.dp))
                    .background(Color(0xFFD3F7D7))
                    .align(Alignment.BottomCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?", color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = volkorn
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Register", color = Color.Blue,
                    fontSize = 24.sp,
                    fontFamily = volkorn,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Routes.Register)
                            authViewmodel.emptyState()
                        },
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}
