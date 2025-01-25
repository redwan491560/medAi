package com.example.medai.ui.theme.viewers

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.medai.R
import com.example.medai.admin.crud.launchToast
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.ShowInfo
import com.example.medai.ui.theme.textFieldColor
import com.example.medai.util.Routes


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MedicineViewerScreen(medicine: Routes.MedicineViewer, navController: NavHostController) {

    var qty by remember {
        mutableIntStateOf(0)
    }
    val chips = listOf("Add to cart", "Generics")
    var chipState by remember {
        mutableIntStateOf(3)
    }
    val bar = listOf("About", "Learn more")
    var barState by remember {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(Color.White),
        floatingActionButton = {
            Image(painter = painterResource(id = R.drawable.cart),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.navigate(Routes.Cart)
                    }
            )
        },
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                    Text(
                        text = "Buy medicine",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(5.dp),
                        fontFamily = volkorn,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 40.dp)
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Balance: ",
                    fontSize = 16.sp,
                    fontFamily = volkorn,
                    color = Color.Blue,
                    modifier = Modifier.padding(5.dp, 0.dp),
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = "1276.46", fontSize = 16.sp, fontFamily = volkorn
                )

            }

//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp), shape = RoundedCornerShape(8.dp)
//            ) {
//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                ) {
//                    Column(
//                        modifier = Modifier.weight(1f),
//                        verticalArrangement = Arrangement.spacedBy(5.dp)
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.medicine),
//                            contentDescription = null
//                        )
//                        Image(
//                            painter = painterResource(id = R.drawable.medicine),
//                            contentDescription = null
//                        )
//                        Image(
//                            painter = painterResource(id = R.drawable.medicine),
//                            contentDescription = null
//                        )
//                    }
//                    Image(
//                        painter = painterResource(id = R.drawable.medicine),
//                        contentDescription = null,
//                        modifier = Modifier.weight(8f)
//                    )
//                }
//            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 5.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = medicine.name,
                            fontSize = 20.sp,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = volkorn,
                            maxLines = 1
                        )
                        Text(
                            text = "tk ${medicine.price}", fontSize = 25.sp, fontFamily = volkorn
                        )
                    }
                    Text(
                        text = medicine.group,
                        fontSize = 17.sp,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = volkorn,
                        maxLines = 1
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = medicine.dosage,
                            fontSize = 17.sp,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = volkorn,
                            maxLines = 1
                        )
                        Image(
                            painter = painterResource(id = R.drawable.add_to_favourite),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    chips.forEachIndexed { index, s ->
                        OutlinedButton(
                            onClick = {
                                chipState = index
                                if (chipState == 0) {
                                    launchToast(context = context, "Added to cart")
                                }
                            },
                            shape = RoundedCornerShape(6.dp),
                            contentPadding = PaddingValues(8.dp, 3.dp)
                        ) {
                            Text(
                                text = s,
                                color = if (index == chipState) Color.Blue else Color.Black
                            )
                        }
                    }
                }

                OutlinedButton(
                    onClick = {

                    }, shape = RoundedCornerShape(6.dp), contentPadding = PaddingValues(10.dp, 5.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.remove),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                if (qty < 0) qty = 0
                                qty--
                            },
                            colorFilter = ColorFilter.tint(Color.Black)
                        )
                        Text(
                            text = if (qty == 0 || qty < 0) "Qty" else qty.toString(),
                            modifier = Modifier.width(35.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                        )
                        Image(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                if (qty < 0) qty = 0
                                qty++
                            },
                            colorFilter = ColorFilter.tint(Color.Black)
                        )

                    }
                }


            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    Modifier.padding(10.dp, 0.dp)
                ) {

                    bar.forEachIndexed { index, s ->
                        Text(text = s,
                            fontFamily = volkorn,
                            fontSize = 16.sp,
                            textDecoration = if (barState == index) {
                                TextDecoration.Underline
                            } else {
                                TextDecoration.None
                            },
                            modifier = Modifier
                                .clickable {
                                    barState = index
                                }
                                .padding(5.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                // information row
                if (barState == 0) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp, 5.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(textFieldColor)
                            .horizontalScroll(
                                rememberScrollState()
                            ), verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Column(
                            Modifier.padding(0.dp, 5.dp)
                        ) {
                            ShowInfo(
                                title = "Name", descriptor = medicine.name
                            )
                            ShowInfo(
                                title = "Group", descriptor = medicine.group
                            )
                            ShowInfo(
                                title = "Price", descriptor = medicine.price.toString() + " tk"
                            )
                            ShowInfo(
                                title = "Description", descriptor = medicine.description.toString()
                            )
                            ShowInfo(
                                title = "Usage", descriptor = medicine.usage
                            )
                            ShowInfo(
                                title = "Dosage", descriptor = medicine.dosage
                            )
                            ShowInfo(
                                title = "Dosage time", descriptor = medicine.dosageTime
                            )
                            ShowInfo(
                                title = "Manufacturer",
                                descriptor = medicine.manufacturer.toString()
                            )
                            ShowInfo(
                                title = "Advice", descriptor = medicine.advice
                            )
                            ShowInfo(
                                title = "Precautions", descriptor = medicine.precautions
                            )
                            ShowInfo(
                                title = "Prerequisite Meds",
                                descriptor = medicine.prerequisiteMeds.toString()
                            )
                            ShowInfo(
                                title = "Supplier", descriptor = medicine.supplier.toString()
                            )
                        }

                    }

                } else {
                    Text(
                        text = "visit to url using the medicine name",
                        fontFamily = volkorn,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp, 5.dp)
                    )
                }


            }
        }
    }

    // content row


}


@Preview(showSystemUi = true)
@Composable
private fun Asdffcd() {
    MedicineViewerScreen(
        Routes.MedicineViewer(
            name = "Seclo 20",
            group = "Omeprazole 200ml I% Omeprazole",
            price = 49,
            usage = "gastric related problems",
            dosage = "I - I - I",
            dosageTime = "30 min before meal",
            description = "Seclo 20 is a medicine used for gastric related problem and ulcer related problems. It provides a significant improvement on pain management and instant swift relief. it comes with a tablet type, capsule type and injection type "
        ), rememberNavController()
    )

}