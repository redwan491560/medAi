package com.example.medai.viewers

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medai.R
import com.example.medai.db.Doctors
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign
import com.example.medai.ui.theme.drawerColor
import com.example.medai.viewmodels.ClickableStateViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DoctorsViewer(doctors: Doctors) {
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

        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun Agfdgd() {
    DoctorsViewer(
        doctors = Doctors(
            image = R.drawable.tanvir_mohit,
            name = "Dr. Tanvir Mohit",
            group = "Medicine",
            visit = "1000"
        )
    )
}