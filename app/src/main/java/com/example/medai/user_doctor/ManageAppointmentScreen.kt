package com.example.medai.user_doctor

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.db.volkorn
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.viewmodels.DatabaseViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManageAppointmentScreen(viewModel: DatabaseViewModel) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextDesign(name = "Manage Appointment")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextDesign(name = "Count: ${viewModel.appointmentList.size}", font = 16)
                Text(
                    text = "Approve",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(5.dp)
                        .clickable {
                            viewModel.appointmentList = viewModel.appointmentList
                                .filter {
                                    !it.approved.value
                                }
                                .toMutableList()
                        },
                    fontFamily = volkorn,
                    textDecoration = TextDecoration.Underline, color = Color.Blue
                )

            }

            // appointment list


            viewModel.appointmentList.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 3.dp),
                    elevation = CardDefaults.cardElevation(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${it.serial + 1}",
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(1f),
                            fontFamily = volkorn
                        )
                        Text(
                            text = it.name,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(6f),
                            fontFamily = volkorn
                        )
                        // Checkbox responds to individual row
                        val checked = it.approved
                        Checkbox(
                            checked = checked.value,
                            onCheckedChange = {
                                checked.value = !checked.value

                            },
                            modifier = Modifier.weight(2f)
                        )
                    }
                }

            }

        }


    }


}


@Preview(showSystemUi = true)
@Composable
private fun sASJjnd() {
    ManageAppointmentScreen(DatabaseViewModel())
}