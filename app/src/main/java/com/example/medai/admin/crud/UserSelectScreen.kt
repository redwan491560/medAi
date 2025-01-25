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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medai.R
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.signup
import com.example.medai.ui.theme.signupCard
import com.example.medai.viewmodels.DatabaseViewModel
import com.example.medai.viewmodels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSelectScreen(viewmodel: DatabaseViewModel) {

    val mainViewModel: MainViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
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
                .fillMaxSize()
                .padding(40.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select User type",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 30.dp),
                    color = Color.White,
                    fontFamily = volkorn,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.Underline
                )

                // select items
                Column(
                    modifier = Modifier
                        .padding(20.dp, 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        var expanded by remember { mutableStateOf(false) }

                        ExposedDropdownMenuBox(
                            expanded = expanded, onExpandedChange = {
                                expanded = !expanded
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                elevation = CardDefaults.cardElevation(8.dp)
                                ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp, 10.dp)
                                        .menuAnchor(MenuAnchorType.SecondaryEditable, true),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = mainViewModel.info.value,
                                        fontSize = 18.sp,
                                        fontFamily = volkorn,
                                        modifier = Modifier.weight(8f), maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Icon(
                                        imageVector = Icons.Outlined.ArrowDropDown,
                                        contentDescription = null
                                    )

                                }
                            }
                            ExposedDropdownMenu(
                                matchTextFieldWidth = true,
                                shape = RoundedCornerShape(10.dp),
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                scrollState = rememberScrollState()
                            ) {

                                mainViewModel.userType.forEachIndexed { index, string ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = string,
                                                fontFamily = volkorn,
                                                fontSize = 14.sp
                                            )
                                        },
                                        onClick = {
                                            mainViewModel.info.value =
                                                mainViewModel.userType[index] // Update selected item
                                            expanded = false // Close dropdown after selection
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                    )

                                }
                            }

                        }

                    }
                    when (mainViewModel.info.value) {
                        "Doctor" -> {
                            TextField(
                                value = viewmodel.bmdcLicense.value,
                                onValueChange = {
                                    viewmodel.bmdcLicense.value = it
                                },
                                textStyle = TextStyle(
                                    fontFamily = volkorn,
                                    fontSize = 14.sp
                                ),
                                singleLine = true,
                                placeholder = {
                                    Text(
                                        text = "BMDC License No.",
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
                        }

                        "Pharmacist" -> {
                            TextField(
                                value = viewmodel.shopName.value,
                                onValueChange = {
                                    viewmodel.shopName.value = it
                                },
                                textStyle = TextStyle(
                                    fontFamily = volkorn,
                                    fontSize = 14.sp
                                ),
                                singleLine = true,
                                placeholder = {
                                    Text(
                                        text = "Shop name",
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
                                value = viewmodel.shopLocation.value,
                                onValueChange = {
                                    viewmodel.shopLocation.value = it
                                },
                                textStyle = TextStyle(
                                    fontFamily = volkorn,
                                    fontSize = 14.sp
                                ),
                                singleLine = true,
                                placeholder = {
                                    Text(
                                        text = "Shop location",
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
                        }

                        "Sales" -> {
                            TextField(
                                value = viewmodel.companyName.value,
                                onValueChange = {
                                    viewmodel.companyName.value = it
                                },
                                textStyle = TextStyle(
                                    fontFamily = volkorn,
                                    fontSize = 14.sp
                                ),
                                singleLine = true,
                                placeholder = {
                                    Text(
                                        text = "Company name",
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
                                value = viewmodel.c_ID.value,
                                onValueChange = {
                                    viewmodel.c_ID.value = it
                                },
                                textStyle = TextStyle(
                                    fontFamily = volkorn,
                                    fontSize = 14.sp
                                ),
                                singleLine = true,
                                placeholder = {
                                    Text(
                                        text = "Company ID",
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
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedButton(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF1790BD)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Register",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(3.dp),
                        fontFamily = volkorn, textAlign = TextAlign.Center, color = Color.Black
                    )
                }


            }


        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun Shap() {
    UserSelectScreen(viewmodel = DatabaseViewModel())
}