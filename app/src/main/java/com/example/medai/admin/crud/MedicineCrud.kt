package com.example.medai.admin.crud

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.R
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign
import com.example.medai.ui.theme.ivory
import com.example.medai.viewmodels.DatabaseViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MedicineCrud(

) {

    val viewmodel = DatabaseViewModel()

    var name by remember {
        mutableStateOf("")
    }
    var group by remember {
        mutableStateOf("")
    }
    var usage by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("")
    }
    var dosage by remember {
        mutableStateOf("")
    }
    var dosageTime by remember {
        mutableStateOf("")
    }
    var advice by remember {
        mutableStateOf("Take with the consultation of a professional doctors")
    }
    var precautions by remember {
        mutableStateOf("Do not provide to children below 16 years")
    }
    var prerequisiteMeds by remember {
        mutableStateOf<String?>("null")
    }
    var image by remember {
        mutableStateOf<Int?>(null)
    }
    var description by remember {
        mutableStateOf<String?>("null")
    }
    var manufacturer by remember {
        mutableStateOf<String?>("null")
    }
    var supplier by remember {
        mutableStateOf<String?>("null")
    }


    Scaffold(
        modifier = Modifier.background(Color.White),
        topBar = {


        }
    ) {
        Column(
            Modifier
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ComposablesDesign.IconsDesign(src = painterResource(id = R.drawable.home)) {

                }
                Spacer(modifier = Modifier.width(20.dp))
                ComposablesDesign.IconsDesign(
                    size = 24,
                    src = painterResource(id = R.drawable.server)
                ) {

                }
            }
            Text(
                text = "Perform CRUD on medicine Table",
                fontSize = 20.sp,
                fontFamily = volkorn,
                modifier = Modifier.padding(0.dp, 10.dp)
            )
            Text(
                text = "* indicates the must required field",
                fontSize = 14.sp,
                fontFamily = volkorn,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 5.dp)
            )
            Card(
                shape = RoundedCornerShape(6.dp),
                elevation = CardDefaults.cardElevation(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = ivory
                ),
                modifier = Modifier.padding(0.dp, 0.dp, 30.dp, 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    TextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        singleLine = true,
                        placeholder = {
                            Text(text = "Name *", fontFamily = volkorn, fontSize = 14.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = group,
                        onValueChange = {
                            group = it
                        },
                        singleLine = true,
                        placeholder = {
                            Text(text = "Group *", fontFamily = volkorn, fontSize = 14.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = price,
                        onValueChange = {
                            price = it
                        },
                        singleLine = true,
                        placeholder = {
                            Text(text = "Price *", fontFamily = volkorn, fontSize = 14.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = usage,
                        onValueChange = {
                            usage = it
                        },
                        singleLine = true,
                        placeholder = {
                            Text(text = "Usage *", fontFamily = volkorn, fontSize = 14.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = dosage,
                        onValueChange = {
                            dosage = it
                        },
                        singleLine = true,
                        placeholder = {
                            Text(text = "Dosage daily *", fontFamily = volkorn, fontSize = 14.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    TextField(
                        value = dosageTime,
                        onValueChange = {
                            dosageTime = it
                        },
                        singleLine = true,
                        placeholder = {
                            Text(text = "Dosage time *", fontFamily = volkorn, fontSize = 14.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )

                    TextField(
                        value = advice,
                        onValueChange = {
                            advice = it
                        },
                        textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),
                        singleLine = true,
                        placeholder = {
                            Text(text = advice, fontFamily = volkorn, fontSize = 14.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )

                    TextField(
                        value = precautions,
                        onValueChange = {
                            precautions = it
                        },
                        singleLine = true,
                        textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),
                        placeholder = {
                            Text(text = "$precautions *", fontFamily = volkorn, fontSize = 14.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )

                    TextField(
                        value = prerequisiteMeds!!,
                        onValueChange = {
                            prerequisiteMeds = it
                        },
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Prerequisite medicines",
                                fontFamily = volkorn,
                                fontSize = 14.sp
                            )
                        }, textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
//
//                    TextField(
//                        value = image!!.toString(),
//                        onValueChange = {
//                            image = it.toInt()
//                        },
//                        singleLine = true,
//                        placeholder = {
//                            Text(text = "Image", fontFamily = volkorn, fontSize = 14.sp)
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(60.dp)
//                            .padding(vertical = 5.dp),
//                        shape = RoundedCornerShape(6.dp),
//                        keyboardActions = KeyboardActions(onDone = {
//
//                        }),
//
//                        colors = TextFieldDefaults.colors(
//                            focusedIndicatorColor = Color.Black,
//                            unfocusedIndicatorColor = Color.Black,
//                            focusedContainerColor = Color.Transparent,
//                            unfocusedContainerColor = Color.Transparent
//                        )
//                    )
//
                    TextField(
                        value = description!!,
                        onValueChange = {
                            description = it
                        },
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Medicine Description",
                                fontFamily = volkorn,
                                fontSize = 14.sp
                            )
                        }, textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )

                    TextField(
                        value = manufacturer!!,
                        onValueChange = {
                            manufacturer = it
                        },
                        singleLine = true,
                        placeholder = {
                            Text(text = "Manufacturer", fontFamily = volkorn, fontSize = 14.sp)
                        }, textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )

                    TextField(
                        value = supplier!!,
                        onValueChange = {
                            supplier = it
                        }, textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),

                        singleLine = true,
                        placeholder = {
                            Text(text = "Supplier", fontFamily = volkorn, fontSize = 14.sp)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(vertical = 5.dp),
                        shape = RoundedCornerShape(6.dp),
                        keyboardActions = KeyboardActions(onDone = {

                        }),

                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Card(
                        elevation = CardDefaults.cardElevation(5.dp),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(5.dp)
                            .clickable {

                            },
                    ) {
                        Text(
                            text = "Create",
                            fontSize = 18.sp,
                            fontFamily = volkorn,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.padding(15.dp, 6.dp)
                        )

                    }
                }
            }
        }
    }
}


@Preview(
    showSystemUi = true
)
@Composable
private fun Ajndfj() {
    MedicineCrud()
}