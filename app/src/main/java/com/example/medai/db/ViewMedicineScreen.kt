package com.example.medai.db

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.components.MedicineDesign
import com.example.medai.util.Routes
import com.example.medai.viewmodels.item.MedicineViewmodel


@Composable
fun ViewMedicineScreen(medicineViewmodel: MedicineViewmodel, navController: NavHostController) {


    LaunchedEffect(Unit) {
        medicineViewmodel.readAllMedicines()
    }

    val medicineList = medicineViewmodel.medicineList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        Row (
            Modifier.padding(bottom = 20.dp)
        ){
            TextDesign(name = "View all medicine", font = 20)
        }
        LazyColumn {
            items(medicineList.value) { item ->
                MedicineDesign(
                    name = item.name, group = item.group, price = item.price, usage = item.usage
                ) {
                    navController.navigate(
                        Routes.MedicineViewer(
                            name = item.name,
                            group = item.group,
                            price = item.price,
                            description = item.description,
                            usage = item.usage,
                            dosage = item.dosage,
                            dosageTime = item.dosageTime,
                            manufacturer = item.manufacturer,
                            advice = item.advice,
                            precautions = item.precautions,
                            prerequisiteMeds = item.prerequisiteMeds,
                            supplier = item.supplier,
                            image = item.image,
                            uid = item.uid
                        )
                    )
                }

            }
        }
    }
}


