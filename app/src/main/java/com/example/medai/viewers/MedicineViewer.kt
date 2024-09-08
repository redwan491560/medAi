package com.example.medai.viewers

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.R
import com.example.medai.db.Medicine
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.ShowInfo
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.ui.theme.textFieldColor
import com.example.medai.viewmodels.ClickableStateViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MedicineViewerScreen(medicine: Medicine) {
    var search by remember {
        mutableStateOf("")
    }
    var qty by remember {
        mutableIntStateOf(0)
    }
    val states = ClickableStateViewModel()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)


    ModalNavigationDrawer(drawerState = drawerState, gesturesEnabled = true, drawerContent = {
        ModalDrawerSheet(
            modifier = Modifier.padding(5.dp),
            drawerTonalElevation = DrawerDefaults.ModalDrawerElevation,
            drawerShape = RoundedCornerShape(10.dp),
            drawerContainerColor = Color(drawerColor.value)
        ) {
            Column(
                modifier = Modifier
                    .size(300.dp)
                    .padding(15.dp, 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextDesign(name = "Settings")
                TextDesign(name = "Personalize")
                TextDesign(name = "Security")
                TextDesign(name = "Developer options")
                TextDesign(name = "Routine checks")
            }
        }
    }) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TextField(
                    value = search,
                    onValueChange = {
                        search = it
                    },
                    textStyle = TextStyle(
                        color = Color.Black, fontFamily = volkorn, fontSize = 16.sp
                    ),
                    singleLine = true,
                    placeholder = {
                        Text(
                            color = Color.Black,
                            text = "Search medicine",
                            fontFamily = volkorn,
                            fontSize = 12.sp,
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 0.dp)
                        .height(60.dp),
                    shape = RoundedCornerShape(6.dp),
                    keyboardActions = KeyboardActions(onDone = {

                    }),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Blue,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(7f)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Medicines",
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

                        }
                        IconsDesign(src = painterResource(id = R.drawable.home)) {

                        }
                        IconsDesign(src = painterResource(id = R.drawable.menus)) {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    }
                }
                // solve this inner padding system


                // content row

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(5.dp, 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.medicine),
                        contentDescription = null,
                        modifier = Modifier
                            .size(130.dp)
                            .weight(3f)
                            .padding(5.dp),
                        alignment = Alignment.CenterStart
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(7f)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = medicine.name,
                                fontSize = 20.sp,
                                overflow = TextOverflow.Ellipsis,
                                fontFamily = volkorn,
                                modifier = Modifier.width(180.dp),
                                maxLines = 2
                            )
                            Text(
                                text = medicine.price.toString() + " tk",
                                fontSize = 24.sp,
                                fontFamily = volkorn
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = medicine.group,
                                fontSize = 16.sp,
                                overflow = TextOverflow.Ellipsis,
                                fontFamily = volkorn,
                                modifier = Modifier.width(180.dp),
                                maxLines = 1
                            )
                            Image(
                                imageVector = Icons.Filled.FavoriteBorder,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "-",
                                    fontSize = 30.sp,
                                    fontFamily = volkorn,
                                    color = Color.Black,
                                    modifier = Modifier.clickable {
                                        qty--
                                    })

                                Card(
                                    shape = RoundedCornerShape(5.dp),
                                    modifier = Modifier.size(50.dp, 35.dp),
                                    colors = CardDefaults.cardColors(drawerColor)
                                ) {
                                    Column(
                                        Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = qty.toString(),
                                            fontSize = 22.sp,
                                            fontFamily = volkorn,
                                            color = Color.Black,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                                Text(text = "+",
                                    fontSize = 30.sp,
                                    fontFamily = volkorn,
                                    color = Color.Black,
                                    modifier = Modifier.clickable {
                                        qty++
                                    })
                            }
                            Image(imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {
                                        if (qty < 0) {
                                            // make toast that item qty cannot be negative
                                            qty = 0
                                        } else {
                                            // upload to cart db
                                        }
                                    })


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
                        Text(text = "About",
                            fontFamily = volkorn,
                            fontSize = 16.sp,
                            textDecoration = if (states.aboutState.intValue == 1) {
                                TextDecoration.Underline
                            } else {
                                TextDecoration.None
                            },
                            modifier = Modifier
                                .clickable {
                                    states.aboutState.intValue = 1
                                    states.learnMoreState.intValue = 0
                                }
                                .padding(5.dp)

                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(text = "Learn more",
                            fontFamily = volkorn,
                            fontSize = 16.sp,
                            textDecoration = if (states.learnMoreState.intValue == 1) {
                                TextDecoration.Underline
                            } else {
                                TextDecoration.None
                            },
                            modifier = Modifier
                                .clickable {
                                    states.aboutState.intValue = 0
                                    states.learnMoreState.intValue = 1
                                }
                                .padding(5.dp)

                        )
                    }

                    // information row
                    if (states.aboutState.intValue == 1) {
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
                                    title = "Name",
                                    descriptor = medicine.name
                                )
                                ShowInfo(
                                    title = "Group",
                                    descriptor = medicine.group
                                )
                                ShowInfo(
                                    title = "Price",
                                    descriptor = medicine.price.toString() + " tk"
                                )
                                ShowInfo(
                                    title = "Description",
                                    descriptor = medicine.description.toString()
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
                            fontSize = 24.sp,
                            modifier = Modifier.padding(10.dp, 5.dp)
                        )
                    }



                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier.padding(10.dp, 5.dp)
                    ) {
                        Text(
                            text = "New",
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable {
                                    states.newState.intValue = 1
                                    states.popularState.intValue = 0
                                    states.savedState.intValue = 0

                                },
                            fontFamily = volkorn,
                            textDecoration = if (states.newState.intValue == 1) {
                                TextDecoration.Underline
                            } else {
                                TextDecoration.None
                            }
                        )
                        Text(
                            text = "Popular",
                            fontSize = 16.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable {
                                    states.newState.intValue = 0
                                    states.popularState.intValue = 1
                                    states.savedState.intValue = 0
                                },
                            fontFamily = volkorn,
                            textDecoration = if (states.popularState.intValue == 1) {
                                TextDecoration.Underline
                            } else {
                                TextDecoration.None
                            }
                        )
                        Text(
                            color = Color.Black,
                            text = "Saved",
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable {
                                    states.newState.intValue = 0
                                    states.popularState.intValue = 0
                                    states.savedState.intValue = 1
                                },
                            fontFamily = volkorn,
                            textDecoration = if (states.savedState.intValue == 1) {
                                TextDecoration.Underline
                            } else {
                                TextDecoration.None
                            }
                        )
                    }

                    Column(
                        Modifier.padding(10.dp, 5.dp)
                    ) {
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                        ComposablesDesign.ArticleDesign(
                            title = "this is the new era",
                            author = "redwan hussain",
                            views = 1234,
                            rating = 3.5.toString()
                        ) {

                        }
                    }

                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun Asdffcd() {
    MedicineViewerScreen(
        Medicine(
            name = "Seclo Seclo 20 Seclo 20Seclo 20 Seclo 20 20",
            group = "Omeprazole 200ml 1% Omeprazole",
            price = 49,
            usage = "gastric related problems",
            dosage = "1-1-1",
            dosageTime = "30 min before meal",
            description = "Seclo 20 is a medicine used for gastric related problem and ulcer related problems. It provides a significant improvement on pain management and instant swift relief. it comes with a tablet type, capsule type and injection type "
        )
    )

}