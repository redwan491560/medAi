package com.example.medai.admin.crud

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.medai.ui.theme.signup
import com.example.medai.ui.theme.signupCard
import com.example.medai.ui.theme.textFieldColor2
import com.example.medai.util.Routes
import com.example.medai.viewmodels.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUp(navController: NavHostController, authViewmodel: AuthViewModel) {


    var userName by remember {
        mutableStateOf("")
    }
    var phone by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    var retypePassword by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val authState = authViewmodel.authState.observeAsState()


    LaunchedEffect(authState.value) {
        when (authState.value) {
            is Status.Error -> Toast.makeText(
                context,
                (authState.value as Status.Error).message,
                Toast.LENGTH_SHORT
            ).show()

            else -> Unit
        }
    }

    var checkedState by remember {
        mutableStateOf(false)
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
                painter = painterResource(id = R.drawable.signup),
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
                    .padding(10.dp, 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(signup)
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(40.dp, 20.dp),
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
                        colorFilter = ColorFilter.tint(Color.White)

                    )
                    Text(
                        text = "MedAi",
                        fontSize = 24.sp,
                        fontFamily = volkorn,
                        color = Color.White
                    )

                }
                Column(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    TextField(
                        value = userName,
                        onValueChange = {
                            userName = it
                        },
                        textStyle = TextStyle(
                            fontFamily = volkorn,
                            fontSize = 14.sp
                        ),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Username",
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
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedPlaceholderColor = Color.White,
                            unfocusedPlaceholderColor = Color.Black,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color(signup.value),
                            unfocusedContainerColor = Color(signupCard.value)
                        )
                    )
                    TextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                        },
                        textStyle = TextStyle(
                            fontFamily = volkorn,
                            fontSize = 14.sp
                        ),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Phone No.",
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
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedPlaceholderColor = Color.White,
                            unfocusedPlaceholderColor = Color.Black,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color(signup.value),
                            unfocusedContainerColor = Color(signupCard.value)
                        )
                    )
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
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedPlaceholderColor = Color.White,
                            unfocusedPlaceholderColor = Color.Black,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color(signup.value),
                            unfocusedContainerColor = Color(signupCard.value)
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
                        placeholder = {
                            Text(
                                text = "Password.",
                                fontFamily = volkorn,
                                fontSize = 14.sp,
                            )

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(0.dp, 5.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedPlaceholderColor = Color.White,
                            unfocusedPlaceholderColor = Color.Black,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color(signup.value),
                            unfocusedContainerColor = Color(signupCard.value)
                        )
                    )

                    TextField(
                        value = retypePassword,
                        onValueChange = {
                            retypePassword = it
                        },
                        textStyle = TextStyle(
                            fontFamily = volkorn, fontSize = 14.sp
                        ),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Retype password",
                                fontFamily = volkorn,
                                fontSize = 14.sp,
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(0.dp, 5.dp)
                            .clip(RoundedCornerShape(10.dp)),

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.Black,
                            focusedPlaceholderColor = Color.White,
                            unfocusedPlaceholderColor = Color.Black,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color(signup.value),
                            unfocusedContainerColor = Color(signupCard.value)
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Checkbox(
                            checked = checkedState,
                            onCheckedChange = {
                                checkedState = !checkedState
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color.Black,
                                uncheckedColor = Color.White
                            )
                        )
                        Text(
                            text = "Agree to the terms and condition",
                            fontFamily = volkorn,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        if (authState.value == Status.Loading) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp))
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedButton(
                            onClick = {
                                // register
                                if (checkedState && password == retypePassword) {
                                    authViewmodel.register(
                                        email = email,
                                        password = password,
                                        phone = phone,
                                        userName = userName
                                    ) {
                                        navController.navigate(Routes.Login)
                                    }

                                } else {
                                    Toast.makeText(
                                        context,
                                        "Password didn't match",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            },
                            colors = ButtonDefaults.buttonColors(textFieldColor2),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Sign Up", color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = volkorn
                            )
                        }
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
                    .background(Color(0xFFABCEEC))
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
                    text = "Already have an account?", color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = volkorn
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Login", color = Color.Blue,
                    fontSize = 24.sp,
                    fontFamily = volkorn,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Routes.Login)
                            authViewmodel.emptyState()
                        },
                    textDecoration = TextDecoration.Underline,

                    )
            }
        }
    }


}

