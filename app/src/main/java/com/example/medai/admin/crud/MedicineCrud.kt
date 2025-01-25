package com.example.medai.admin.crud

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.medai.R
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ivory
import com.example.medai.util.Routes
import com.example.medai.viewmodels.item.MedicineViewmodel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MedicineCrud(medicineViewmodel: MedicineViewmodel, navController: NavHostController) {


    val chips = listOf("Create", "Update", "Delete")
    var chipState by remember {
        mutableIntStateOf(0)
    }

    var medicineItem by remember {
        mutableStateOf<Routes.MedicineViewer?>(null)
    }


    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var group by remember { mutableStateOf("") }
    var usage by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    var dosageTime by remember { mutableStateOf("") }

//    var advice by remember { mutableStateOf("Take with the consultation of a professional doctors") }
//    var precautions by remember { mutableStateOf("Do not provide to children below 16 years") }
//    var prerequisiteMeds by remember { mutableStateOf<String?>("null") }
//    var description by remember { mutableStateOf<String?>("null") }
//    var manufacturer by remember { mutableStateOf<String?>("null") }
//    var supplier by remember { mutableStateOf<String?>("null") }


    Scaffold(
        modifier = Modifier
            .background(Color.White)
            .systemBarsPadding(),
        topBar = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Medicine Database",
                    fontSize = 20.sp,
                    fontFamily = volkorn,
                    modifier = Modifier.padding(10.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 15.dp)
                ) {
                    IconsDesign(size = 35, src = painterResource(id = R.drawable.home)) {
                        navController.navigate(Routes.Main)
                    }
                    IconsDesign(
                        size = 30, src = painterResource(id = R.drawable.server)
                    ) {
                        navController.navigate(Routes.ViewMedicine)
                    }
                }
            }
        }) {
        Column(
            Modifier
                .padding(top = 50.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "* must required field",
                fontSize = 14.sp,
                fontFamily = volkorn,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
            )

            Row {
                chips.forEachIndexed { index, string ->
                    Text(text = string,
                        fontFamily = volkorn,
                        fontSize = 18.sp,
                        color = if (chipState == index) {
                            Color.Blue
                        } else {
                            Color.Black
                        },
                        textDecoration = if (chipState == index) {
                            TextDecoration.Underline
                        } else {
                            TextDecoration.None
                        },
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clickable {
                                chipState = index
                            })
                }
            }

            Card(
                shape = RoundedCornerShape(6.dp),
                elevation = CardDefaults.cardElevation(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = ivory
                ),
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {


                    if (chipState == 1 || chipState == 2) {
                        // id
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xD5B1F7F7)),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (id.isEmpty()) {
                                Text(
                                    text = "Medicine ID *",
                                    fontFamily = volkorn,
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 15.dp)
                                )
                            }
                            BasicTextField(
                                value = id,
                                onValueChange = {
                                    id = it
                                },
                                modifier = Modifier
                                    .padding(horizontal = 15.dp, vertical = 10.dp)
                                    .fillMaxWidth(),
                                textStyle = TextStyle(
                                    fontFamily = volkorn, fontSize = 18.sp, color = Color.Black
                                ),
                                keyboardActions = KeyboardActions(onDone = {
                                    // search for medicine using id
                                    if (id.isEmpty()) {
                                        launchToast(context, "Medicine ID is empty")
                                    } else {
                                        medicineViewmodel.searchMedicineById(id) { meds ->
                                            if (meds == null) {
                                                medicineItem = null
                                                launchToast(
                                                    context,
                                                    "Medicine do not exist"
                                                )
                                            } else {
                                                medicineItem = meds
                                                name = meds.name
                                                group = meds.group
                                                price = meds.price.toString()
                                                usage = meds.usage
                                                dosage = meds.dosage
                                                dosageTime = meds.dosageTime
                                            }
                                        }
                                    }
                                }),
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                )
                            )
                            Image(painter = painterResource(id = R.drawable.search),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(horizontal = 15.dp, vertical = 4.dp)
                                    .size(20.dp)
                                    .rotate(270f)
                                    .clickable {
                                        // search for medicine using id
                                        if (id.isEmpty()) {
                                            launchToast(context, "Medicine ID is empty")
                                        } else {
                                            medicineViewmodel.searchMedicineById(id) { meds ->
                                                if (meds == null) {
                                                    medicineItem = null
                                                    launchToast(
                                                        context,
                                                        "Medicine do not exist"
                                                    )
                                                } else {
                                                    medicineItem = meds
                                                    name = meds.name
                                                    group = meds.group
                                                    price = meds.price.toString()
                                                    usage = meds.usage
                                                    dosage = meds.dosage
                                                    dosageTime = meds.dosageTime
                                                }
                                            }
                                        }
                                    })
                        }

                    }


                    // name
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5B1F7F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (name.isEmpty()) {
                            Text(
                                text = "Medicine name *",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = name,
                            onValueChange = {
                                name = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn, fontSize = 18.sp, color = Color.Black
                            ),
                            readOnly = (chipState == 2),
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    // group
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5B1F7F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (group.isEmpty()) {
                            Text(
                                text = "Group *",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = group,
                            onValueChange = {
                                group = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn, fontSize = 18.sp, color = Color.Black
                            ),
                            readOnly = (chipState == 2 || chipState == 1),
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }


                    // price
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xD5B1F7F7)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (price.isEmpty()) {
                            Text(
                                text = "Price *",
                                fontFamily = volkorn,
                                fontSize = 16.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(start = 15.dp)
                            )
                        }
                        BasicTextField(
                            value = price,
                            onValueChange = {
                                price = it
                            },
                            modifier = Modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            textStyle = TextStyle(
                                fontFamily = volkorn, fontSize = 18.sp, color = Color.Black
                            ),
                            readOnly = (chipState == 2),
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            )
                        )
                    }

                    if (chipState == 0 || chipState == 1) {
                        // usage
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xD5B1F7F7)),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (usage.isEmpty()) {
                                Text(
                                    text = "Usage *",
                                    fontFamily = volkorn,
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 15.dp)
                                )
                            }
                            BasicTextField(
                                value = usage,
                                onValueChange = {
                                    usage = it
                                },
                                modifier = Modifier
                                    .padding(horizontal = 15.dp, vertical = 10.dp)
                                    .fillMaxWidth(),
                                textStyle = TextStyle(
                                    fontFamily = volkorn, fontSize = 18.sp, color = Color.Black
                                ),
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                )
                            )
                        }


                        // dosage
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xD5D7FAD4)),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (dosage.isEmpty()) {
                                Text(
                                    text = "Dosage",
                                    fontFamily = volkorn,
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 15.dp)
                                )
                            }
                            BasicTextField(
                                value = dosage,
                                onValueChange = {
                                    dosage = it
                                },
                                modifier = Modifier
                                    .padding(horizontal = 15.dp, vertical = 10.dp)
                                    .fillMaxWidth(),
                                textStyle = TextStyle(
                                    fontFamily = volkorn, fontSize = 18.sp, color = Color.Black
                                ),
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                )
                            )
                        }

                        // dosage time
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xD5D7FAD4)),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (dosageTime.isEmpty()) {
                                Text(
                                    text = "Dosage time",
                                    fontFamily = volkorn,
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 15.dp)
                                )
                            }
                            BasicTextField(
                                value = dosageTime,
                                onValueChange = {
                                    dosageTime = it
                                },
                                modifier = Modifier
                                    .padding(horizontal = 15.dp, vertical = 10.dp)
                                    .fillMaxWidth(),
                                textStyle = TextStyle(
                                    fontFamily = volkorn, fontSize = 18.sp, color = Color.Black
                                ),
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                )
                            )
                        }

//                        // advice
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 10.dp)
//                                .clip(RoundedCornerShape(8.dp))
//                                .background(Color(0xD5D7FAD4)),
//                            contentAlignment = Alignment.CenterStart
//                        ) {
//                            if (advice.isEmpty()) {
//                                Text(
//                                    text = "Advice",
//                                    fontFamily = volkorn,
//                                    fontSize = 16.sp,
//                                    color = Color.Black,
//                                    modifier = Modifier.padding(start = 15.dp)
//                                )
//                            }
//                            BasicTextField(
//                                value = advice,
//                                onValueChange = {
//                                    advice = it
//                                },
//                                modifier = Modifier
//                                    .padding(horizontal = 15.dp, vertical = 10.dp)
//                                    .fillMaxWidth(),
//                                textStyle = TextStyle(
//                                    fontFamily = volkorn, fontSize = 16.sp, color = Color.Black
//                                ),
//                                maxLines = 1,
//                                keyboardOptions = KeyboardOptions(
//                                    imeAction = ImeAction.Next
//                                )
//                            )
//                        }
                    }


//                    TextField(
//                        value = precautions,
//                        onValueChange = {
//                            precautions = it
//                        },
//                        singleLine = true,
//                        textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),
//                        placeholder = {
//                            Text(text = "$precautions *", fontFamily = volkorn, fontSize = 14.sp)
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

//                    TextField(
//                        value = prerequisiteMeds!!,
//                        onValueChange = {
//                            prerequisiteMeds = it
//                        },
//                        singleLine = true,
//                        placeholder = {
//                            Text(
//                                text = "Prerequisite medicines",
//                                fontFamily = volkorn,
//                                fontSize = 14.sp
//                            )
//                        }, textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),
//
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

//                    TextField(
//                        value = description!!,
//                        onValueChange = {
//                            description = it
//                        },
//                        singleLine = true,
//                        placeholder = {
//                            Text(
//                                text = "Medicine Description",
//                                fontFamily = volkorn,
//                                fontSize = 14.sp
//                            )
//                        }, textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),
//
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

//                    TextField(
//                        value = manufacturer!!,
//                        onValueChange = {
//                            manufacturer = it
//                        },
//                        singleLine = true,
//                        placeholder = {
//                            Text(text = "Manufacturer", fontFamily = volkorn, fontSize = 14.sp)
//                        }, textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),
//
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
//                    TextField(
//                        value = supplier!!,
//                        onValueChange = {
//                            supplier = it
//                        }, textStyle = TextStyle(fontFamily = volkorn, fontSize = 14.sp),
//
//                        singleLine = true,
//                        placeholder = {
//                            Text(text = "Supplier", fontFamily = volkorn, fontSize = 14.sp)
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

                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Card(
                            elevation = CardDefaults.cardElevation(5.dp),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .padding(bottom = 10.dp, end = 10.dp)
                                .clickable {
                                    when (chipState) {
                                        0 -> {
                                            if (name.isEmpty() || group.isEmpty() || price.isEmpty()) {
                                                launchToast(
                                                    context,
                                                    "Required fields cannot be empty"
                                                )
                                            } else {
                                                medicineViewmodel.createMedicine(
                                                    name = name,
                                                    group = group,
                                                    price = price,
                                                    usage = usage,
                                                    dosage = dosage,
                                                    dosageTime = dosageTime
                                                ) {
                                                    launchToast(context, "Upload Successful")
                                                }
                                                name = ""
                                                group = ""
                                                price = ""
                                                usage = ""
                                                dosage = ""
                                                dosageTime = ""
                                            }
                                        }

                                        1 -> {

                                            if (id.isEmpty()) {
                                                launchToast(context, "Id cannot be empty")
                                            } else {
                                                if (medicineItem == null) {
                                                    launchToast(context, "Medicine does not exist")
                                                } else {
                                                    // Check if there are changes in the fields
                                                    val updates = medicineViewmodel.createUpdateMap(
                                                        name = if (name.trim() != medicineItem!!.name.trim()) name else "",
                                                        price = if (price.trim() != medicineItem!!.price
                                                                .toString()
                                                                .trim()
                                                        ) price else "",
                                                        usage = if (usage.trim() != medicineItem!!.usage.trim()) usage else "",
                                                        dosage = if (dosage.trim() != medicineItem!!.dosage.trim()) dosage else "",
                                                        dosageTime = if (dosageTime.trim() != medicineItem!!.dosageTime.trim()) dosageTime else ""
                                                    )

                                                    medicineViewmodel.updateMedicineById(
                                                        uid = medicineItem!!.uid,
                                                        updates = updates
                                                    ) {
                                                        if (it) {
                                                            launchToast(
                                                                context,
                                                                "Update Successful"
                                                            )
                                                            name = ""
                                                            group = ""
                                                            price = ""
                                                            usage = ""
                                                            dosage = ""
                                                            dosageTime = ""
                                                        } else launchToast(
                                                            context,
                                                            "Error occurred"
                                                        )
                                                    }


                                                    // Clear the fields after update


                                                }
                                            }


                                        }

                                        else -> {
                                            if (id.isEmpty()) {
                                                launchToast(context, "Id cannot be empty")
                                            } else {

                                                if (medicineItem == null) {
                                                    launchToast(
                                                        context,
                                                        "Medicine do not exist"
                                                    )
                                                } else {
                                                    medicineItem?.let {
                                                        name = medicineItem!!.name
                                                        group = medicineItem!!.group
                                                        price = medicineItem!!.price.toString()
                                                        medicineViewmodel.deleteMedicineById(
                                                            medicineItem!!.uid
                                                        )
                                                        launchToast(context, "delete Successful")
                                                        medicineItem = null
                                                    }
                                                    id = ""
                                                    name = ""
                                                    group = ""
                                                    price = ""
                                                    usage = ""
                                                    dosage = ""
                                                    dosageTime = ""
                                                }
                                            }
                                        }
                                    }
                                },
                        ) {
                            Text(
                                text = when (chipState) {
                                    0 -> "Create"
                                    1 -> "Update"
                                    else -> "Delete"
                                },
                                fontSize = 18.sp,
                                fontFamily = volkorn,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.padding(15.dp, 8.dp)
                            )

                        }

                    }

                }
            }
        }
    }
}

fun launchToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}


@Preview(showSystemUi = true)
@Composable
private fun Mehgj() {
    //   MedicineCrud(,rememberNavController())
}
