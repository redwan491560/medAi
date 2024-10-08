package com.example.medai.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.R
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.DropdownCard
import com.example.medai.viewmodels.MainViewModel

class ComposablesDesign {
    companion object {
        @Composable
        fun TextDesign(modifier: Modifier = Modifier, name: String, font: Int = 16) {
            Text(
                text = name,
                fontSize = font.sp,
                modifier = modifier.padding(5.dp),
                fontFamily = volkorn
            )
        }


        @Composable
        fun BarTextDesign(
            modifier: Modifier = Modifier, name: String, font: Int = 16, onClick: () -> Unit
        ) {
            Text(
                text = name, fontSize = font.sp, modifier = modifier
                    .padding(5.dp)
                    .clickable {
                        onClick()
                    }, fontFamily = volkorn
            )
        }

        @Composable
        fun IconsDesign(
            modifier: Modifier = Modifier, size: Int = 28, src: Painter, onClick: () -> Unit
        ) {
            Image(painter = src,
                contentDescription = null,
                modifier = modifier
                    .size(size.dp)
                    .clickable {
                        onClick()
                    }
            )
        }

        @Composable
        fun NavigationBarIcon(title: String, icon: Painter, onClick: () -> Unit) {
            Card(
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Image(painter = icon,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp, 10.dp)
                            .size(20.dp)
                            .clickable { onClick() })
                    Text(
                        text = title, fontSize = 14.sp,
                        modifier = Modifier.padding(10.dp, 5.dp), fontFamily = volkorn
                    )

                }

            }

        }

        @Composable
        fun MedicineDesign(name: String, usage: String, price: Int, group: String) {
            Card(elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(6.dp),
                onClick = {
                    // navigate to cart
                }) {
                Column(
                    Modifier.padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = name, fontSize = 16.sp, fontFamily = volkorn)
                    Text(text = group, fontSize = 12.sp, fontFamily = volkorn)
                    Text(
                        text = "tk $price",
                        fontSize = 16.sp, color = Color.Red,
                        fontFamily = volkorn,
                    )
                }
            }
        }

        @Composable
        fun DoctorDesign(name: String, group: String, visit: String, image: Int) {
            OutlinedCard(
                shape = RoundedCornerShape(6.dp),
                onClick = {

                },
                elevation = CardDefaults.cardElevation(5.dp),
                border = BorderStroke(0.dp, Color.Transparent)
            ) {
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        horizontalAlignment = Alignment.Start, modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = name, fontFamily = volkorn, fontSize = 18.sp)
                        Text(text = "($group)", fontFamily = volkorn, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Fees: $visit tk", fontFamily = volkorn, fontSize = 18.sp)
                    }
                }

            }
        }

        @Composable
        fun ArticleDesign(
            title: String, author: String, views: Int, rating: String, onClick: () -> Unit
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 5.dp, 0.dp, 5.dp),
                shape = RoundedCornerShape(4.dp),
                onClick = {
                    // why is this a error
                    onClick()
                },
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(9f)
                    ) {
                        Text(text = title, fontFamily = volkorn, fontSize = 16.sp)
                        Text(text = "by- $author", fontFamily = volkorn, fontSize = 13.sp)
                    }
                    Column(
                        modifier = Modifier.weight(2f), horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "$rating rated", fontFamily = volkorn, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "$views views", fontFamily = volkorn, fontSize = 12.sp)

                    }
                }

            }
        }


        @Composable
        fun DoctorResult() {
            Card {
                Column {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.tanvir_mohit),
                            contentDescription = null
                        )
                        Column {
                            Text(text = "name")
                            Text(text = "qualification")
                            Text(text = "profession")
                            Text(text = "workplace")
                        }
                    }
                    Row {
                        Text(text = "Department")
                        Text(text = "Book Appointment")

                    }
                }
            }

        }

        @Composable
        fun ShowInfo(title: String, descriptor: String) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .size(150.dp, 30.dp)
                        .padding(5.dp),
                    fontFamily = volkorn,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = descriptor,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .width(400.dp)
                        .padding(5.dp),
                    fontFamily = volkorn,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )

            }

        }

        @Composable
        fun ExerciseIcon(image: Int, title: String, onClick: () -> Unit) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp)
                    .clickable {
                        onClick()
                    },
                shape = RoundedCornerShape(6.dp),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(50.dp)
                    )
                    TextDesign(name = title)
                    Image(
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(5.dp)
                            .size(30.dp)
                    )
                }
            }

        }


        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun RowScope.DropdownCard(
            itemsList: List<String>,
            info: MutableState<String>,
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
                    elevation = CardDefaults.cardElevation(8.dp),

                    ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 10.dp)
                            .menuAnchor(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = info.value,
                            fontSize = 18.sp,
                            fontFamily = volkorn,
                            modifier = Modifier.weight(8f), maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Icon(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = null,)

                    }
                }
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .padding(3.dp), scrollState = rememberScrollState()
                ) {
                    itemsList.forEachIndexed { index, string ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = string,
                                    fontFamily = volkorn,
                                    fontSize = 14.sp
                                )
                            },
                            onClick = {
                                info.value = itemsList[index] // Update selected item
                                expanded = false // Close dropdown after selection
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )

                    }
                }

            }
        }

    }


}

@Preview(showSystemUi = true)
@Composable
fun Adjdk() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        val selectedDepartment =
            remember { mutableStateOf("Location") } // Holds the selected department
        val mainViewModel = MainViewModel()
//        ComposablesDesign.DropdownMenus(
//            departmentList = mainViewModel.departmentList,
//            selectedDepartment = mainViewModel.department, // Binding to ViewModel's state
//            label = "Select Department" // Optional, you can customize the label
//        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 10.dp), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DropdownCard(mainViewModel.locationList, selectedDepartment)
            // DropdownCard(mainViewModel.locationList, selectedDepartment)
        }

    }


}
