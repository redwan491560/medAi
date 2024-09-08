package com.example.medai.user

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medai.R
import com.example.medai.db.Screens
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.NavigationBarIcon
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.doc
import com.example.medai.ui.theme.drawerColor
import com.example.medai.viewmodels.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookAppointment(navController: NavHostController, mainViewModel: MainViewModel) {


    var chipState by remember {
        mutableIntStateOf(0)
    }
    val chipsDocs = listOf("Online", "Onsite")

    var presentState by remember {
        mutableStateOf(true)
    }

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .padding(10.dp, 5.dp),
            drawerTonalElevation = DrawerDefaults.ModalDrawerElevation,
            drawerContainerColor = Color(drawerColor.value),
            drawerShape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp, 5.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                NavigationBarIcon(
                    title = "Settings", icon = painterResource(id = R.drawable.settings)
                ) {

                }
                NavigationBarIcon(
                    title = "Find Blood", icon = painterResource(id = R.drawable.find_blood)
                ) {

                }
                NavigationBarIcon(
                    title = "Donate Blood", icon = painterResource(id = R.drawable.donate_blood)
                ) {

                }
                NavigationBarIcon(
                    title = "Personalize", icon = painterResource(id = R.drawable.personalize)
                ) {

                }
                NavigationBarIcon(
                    title = "Routine check up",
                    icon = painterResource(id = R.drawable.routine_checkup)
                ) {

                }

                NavigationBarIcon(
                    title = "Security", icon = painterResource(id = R.drawable.security)
                ) {

                }
                NavigationBarIcon(
                    title = "Developer options", icon = painterResource(id = R.drawable.dev_ops)
                ) {

                }
                NavigationBarIcon(
                    title = "Report bug", icon = painterResource(id = R.drawable.report_bug)
                ) {

                }

                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Row(
                        Modifier.padding(10.dp, 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconsDesign(
                            src = painterResource(id = R.drawable.app_logo), size = 50
                        ) {

                        }
                        TextDesign(name = "MedAi", font = 20)
                    }
                }

            }
        }
    }) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .systemBarsPadding()
                .padding(5.dp)
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(7f)
                            .padding(10.dp, 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Book Appointment",
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontFamily = volkorn
                        )
                    }
                    Row(
                        modifier = Modifier.weight(4f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconsDesign(src = painterResource(id = R.drawable.cart)) {
                            navController.navigate(Screens.CartScreen.name)

                        }
                        IconsDesign(src = painterResource(id = R.drawable.home)) {
                            navController.navigate(Screens.MainScreen.name)

                        }
                        IconsDesign(src = painterResource(id = R.drawable.menus)) {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Box {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp, 5.dp),
                        ) {
                            chipsDocs.forEachIndexed { index, string ->
                                Text(text = string,
                                    fontFamily = volkorn,
                                    fontSize = 20.sp,
                                    color = if (chipState == index) Color.Blue
                                    else Color.Black,
                                    textDecoration = if (chipState == index) TextDecoration.Underline
                                    else TextDecoration.None,
                                    modifier = Modifier.clickable {
                                        chipState = index
                                        if (index == 0)
                                        {
                                            presentState = true
                                        } else{
                                            presentState = false

                                        }
                                    })
                            }

                        }


                        // location, hospital name, department, doctors name, date
                        // after selecting all the feeds it will show a serial number and when the doctor starts his chamber

                        if (presentState) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                // location

                                var expLoc by remember { mutableStateOf(false) }

                                ExposedDropdownMenuBox(
                                    expanded = expLoc, onExpandedChange = {
                                        expLoc = !expLoc
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    OutlinedTextField(modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                        value = mainViewModel.location.value,
                                        onValueChange = {},
                                        readOnly = true,
                                        placeholder = {
                                            TextDesign(name = "Select Location")
                                        },
                                        textStyle = TextStyle(
                                            fontFamily = volkorn,
                                            fontSize = 18.sp
                                        ),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expLoc
                                            )
                                        })
                                    ExposedDropdownMenu(
                                        expanded = expLoc,
                                        onDismissRequest = { expLoc = false },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentSize(Alignment.TopStart)
                                    ) {
                                        mainViewModel.locationList.forEachIndexed { index, string ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    text = string,
                                                    fontFamily = volkorn,
                                                    fontSize = 18.sp,
                                                )
                                            }, onClick = {
                                                mainViewModel.location.value =
                                                    mainViewModel.locationList[index]
                                                expLoc = false
                                            }, modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }

                                // hospital

                                var hosExp by remember { mutableStateOf(false) }


                                ExposedDropdownMenuBox(
                                    expanded = hosExp, onExpandedChange = {
                                        hosExp = !hosExp
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                        value = mainViewModel.hospital.value,
                                        onValueChange = {},
                                        readOnly = true,
                                        placeholder = {
                                            TextDesign(name = "Select Hospital")
                                        },
                                        textStyle = TextStyle(
                                            fontFamily = volkorn,
                                            fontSize = 18.sp
                                        ),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = hosExp
                                            )
                                        })
                                    ExposedDropdownMenu(
                                        expanded = hosExp,
                                        onDismissRequest = { hosExp = false },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentSize(Alignment.TopStart)
                                    ) {
                                        mainViewModel.hospitalList.forEachIndexed { index, string ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    text = string,
                                                    fontFamily = volkorn,
                                                    fontSize = 18.sp,
                                                )
                                            }, onClick = {
                                                mainViewModel.hospital.value =
                                                    mainViewModel.hospitalList[index]
                                                hosExp = false
                                            }, modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }

                                // department

                                var deptExp by remember { mutableStateOf(false) }


                                ExposedDropdownMenuBox(
                                    expanded = deptExp, onExpandedChange = {
                                        deptExp = !deptExp
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                        value = mainViewModel.department.value,
                                        onValueChange = {},
                                        placeholder = {
                                            TextDesign(name = "Select Department")
                                        },
                                        readOnly = true,
                                        textStyle = TextStyle(
                                            fontFamily = volkorn,
                                            fontSize = 18.sp
                                        ),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = deptExp
                                            )
                                        })
                                    ExposedDropdownMenu(
                                        expanded = deptExp,
                                        onDismissRequest = { deptExp = false },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentSize(Alignment.TopStart)
                                    ) {
                                        mainViewModel.departmentList.forEachIndexed { index, string ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    text = string,
                                                    fontFamily = volkorn,
                                                    fontSize = 18.sp,
                                                )
                                            }, onClick = {
                                                mainViewModel.department.value =
                                                    mainViewModel.departmentList[index]
                                                deptExp = false
                                            }, modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }


                                // doctor

                                var docExp by remember { mutableStateOf(false) }


                                ExposedDropdownMenuBox(
                                    expanded = docExp, onExpandedChange = {
                                        docExp = !docExp
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                        value = mainViewModel.doctor.value,
                                        onValueChange = {},
                                        placeholder = {
                                            TextDesign(name = "Doctor's name")
                                        },
                                        readOnly = true,
                                        textStyle = TextStyle(
                                            fontFamily = volkorn,
                                            fontSize = 18.sp
                                        ),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = docExp
                                            )
                                        })
                                    ExposedDropdownMenu(
                                        expanded = docExp,
                                        onDismissRequest = { docExp = false },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentSize(Alignment.TopStart)
                                    ) {
                                        mainViewModel.doctorsList.forEachIndexed { index, string ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    text = string,
                                                    fontFamily = volkorn,
                                                    fontSize = 18.sp,
                                                )
                                            }, onClick = {
                                                mainViewModel.doctor.value =
                                                    mainViewModel.doctorsList[index]
                                                docExp = false
                                            }, modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }

                                // time
                                // fetch doctors active hour and show on the list


                                var timeExp by remember { mutableStateOf(false) }

                                ExposedDropdownMenuBox(
                                    expanded = timeExp, onExpandedChange = {
                                        timeExp = !timeExp
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                        value = mainViewModel.time.value,
                                        onValueChange = {},
                                        readOnly = true,
                                        placeholder = {
                                            TextDesign(name = "Schedule time")
                                        },
                                        textStyle = TextStyle(
                                            fontFamily = volkorn,
                                            fontSize = 18.sp
                                        ),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = timeExp
                                            )
                                        })
                                    ExposedDropdownMenu(
                                        expanded = timeExp,
                                        onDismissRequest = { timeExp = false },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentSize(Alignment.TopStart)
                                    ) {
                                        mainViewModel.timeList.forEachIndexed { index, string ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    text = string,
                                                    fontFamily = volkorn,
                                                    fontSize = 18.sp,
                                                )
                                            }, onClick = {
                                                mainViewModel.time.value =
                                                    mainViewModel.timeList[index]
                                                timeExp = false
                                            }, modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }


                            }
                        } else {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                // location

                                var expLoc by remember { mutableStateOf(false) }

                                ExposedDropdownMenuBox(
                                    expanded = expLoc, onExpandedChange = {
                                        expLoc = !expLoc
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    OutlinedTextField(modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                        value = mainViewModel.location.value,
                                        onValueChange = {},
                                        readOnly = true,
                                        placeholder = {
                                            TextDesign(name = "Select Location")
                                        },
                                        textStyle = TextStyle(
                                            fontFamily = volkorn,
                                            fontSize = 18.sp
                                        ),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expLoc
                                            )
                                        })
                                    ExposedDropdownMenu(
                                        expanded = expLoc,
                                        onDismissRequest = { expLoc = false },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentSize(Alignment.TopStart)
                                    ) {
                                        mainViewModel.locationList.forEachIndexed { index, string ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    text = string,
                                                    fontFamily = volkorn,
                                                    fontSize = 18.sp,
                                                )
                                            }, onClick = {
                                                mainViewModel.location.value =
                                                    mainViewModel.locationList[index]
                                                expLoc = false
                                            }, modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }

                                // hospital

                                var hosExp by remember { mutableStateOf(false) }


                                ExposedDropdownMenuBox(
                                    expanded = hosExp, onExpandedChange = {
                                        hosExp = !hosExp
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                        value = mainViewModel.hospital.value,
                                        onValueChange = {},
                                        readOnly = true,
                                        placeholder = {
                                            TextDesign(name = "Select Hospital")
                                        },
                                        textStyle = TextStyle(
                                            fontFamily = volkorn,
                                            fontSize = 18.sp
                                        ),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = hosExp
                                            )
                                        })
                                    ExposedDropdownMenu(
                                        expanded = hosExp,
                                        onDismissRequest = { hosExp = false },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentSize(Alignment.TopStart)
                                    ) {
                                        mainViewModel.hospitalList.forEachIndexed { index, string ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    text = string,
                                                    fontFamily = volkorn,
                                                    fontSize = 18.sp,
                                                )
                                            }, onClick = {
                                                mainViewModel.hospital.value =
                                                    mainViewModel.hospitalList[index]
                                                hosExp = false
                                            }, modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }

                                // department

                                var deptExp by remember { mutableStateOf(false) }


                                ExposedDropdownMenuBox(
                                    expanded = deptExp, onExpandedChange = {
                                        deptExp = !deptExp
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                        value = mainViewModel.department.value,
                                        onValueChange = {},
                                        placeholder = {
                                            TextDesign(name = "Select Department")
                                        },
                                        readOnly = true,
                                        textStyle = TextStyle(
                                            fontFamily = volkorn,
                                            fontSize = 18.sp
                                        ),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = deptExp
                                            )
                                        })
                                    ExposedDropdownMenu(
                                        expanded = deptExp,
                                        onDismissRequest = { deptExp = false },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentSize(Alignment.TopStart)
                                    ) {
                                        mainViewModel.departmentList.forEachIndexed { index, string ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    text = string,
                                                    fontFamily = volkorn,
                                                    fontSize = 18.sp,
                                                )
                                            }, onClick = {
                                                mainViewModel.department.value =
                                                    mainViewModel.departmentList[index]
                                                deptExp = false
                                            }, modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }


                                // doctor

                                var docExp by remember { mutableStateOf(false) }


                                ExposedDropdownMenuBox(
                                    expanded = docExp, onExpandedChange = {
                                        docExp = !docExp
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                        value = mainViewModel.doctor.value,
                                        onValueChange = {},
                                        placeholder = {
                                            TextDesign(name = "Doctor's name")
                                        },
                                        readOnly = true,
                                        textStyle = TextStyle(
                                            fontFamily = volkorn,
                                            fontSize = 18.sp
                                        ),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = docExp
                                            )
                                        })
                                    ExposedDropdownMenu(
                                        expanded = docExp,
                                        onDismissRequest = { docExp = false },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentSize(Alignment.TopStart)
                                    ) {
                                        mainViewModel.doctorsList.forEachIndexed { index, string ->
                                            DropdownMenuItem(text = {
                                                Text(
                                                    text = string,
                                                    fontFamily = volkorn,
                                                    fontSize = 18.sp,
                                                )
                                            }, onClick = {
                                                mainViewModel.doctor.value =
                                                    mainViewModel.doctorsList[index]
                                                docExp = false
                                            }, modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    }
                                }


                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(doc)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp, 5.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(5f),
                                    verticalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    TextDesign(name = "Hospital " + mainViewModel.hospital.value)
                                    TextDesign(name = "Doctors name " + mainViewModel.doctor.value)
                                    TextDesign(name = "Scheduled Time" + mainViewModel.time.value)

                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(5f)
                                ) {
                                    TextDesign(name = "Location " + mainViewModel.location.value)
                                    TextDesign(name = "Department " + mainViewModel.department.value)

                                }
                            }
                        }

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(doc)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Row(
                                    Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.tanvir_mohit),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .size(100.dp)
                                            .clip(RoundedCornerShape(6.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                    Column(
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.spacedBy(4.dp),
                                        modifier = Modifier.padding(10.dp, 10.dp)
                                    ) {
                                        Text(
                                            text = "", fontFamily = volkorn, fontSize = 20.sp
                                        )
                                        Text(
                                            text = "MBBS, BCS (Health), FCPS (Medicine)",
                                            fontFamily = volkorn,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            fontSize = 16.sp
                                        )
                                        Spacer(modifier = Modifier.height(3.dp))
                                        Text(
                                            text = "Assistant professor, Medicine",
                                            fontFamily = volkorn,
                                            fontSize = 14.sp, maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                        Text(
                                            text = "Sylhet MAG Osmani Medical College and Hospital",
                                            fontFamily = volkorn,
                                            fontSize = 14.sp, maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                        )
                                    }
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp, 5.dp),
                                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                                ) {
                                    Text(
                                        text = "(Medicine)",
                                        fontFamily = volkorn,
                                        fontSize = 18.sp, maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                    Text(text = "Book Appointment",
                                        fontFamily = volkorn,
                                        color = Color.Blue,
                                        fontSize = 14.sp,
                                        maxLines = 2,
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier.clickable {
                                            // make appointment
                                        })
                                }

                            }

                        }

                        Spacer(modifier = Modifier.height(100.dp))

                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Adfdddj() {
    BookAppointment(navController = rememberNavController(), mainViewModel = MainViewModel())
}