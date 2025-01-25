package com.example.medai.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medai.R
import com.example.medai.ui.theme.ComposablesDesign.Companion.IconsDesign
import com.example.medai.ui.theme.ComposablesDesign.Companion.NavigationBarIcon
import com.example.medai.ui.theme.ComposablesDesign.Companion.TextDesign

@Composable
fun DrawerSheetComposable(
    onMaintainClick: () -> Unit,
    onQNAClick: () -> Unit,
    onProfileClick: () -> Unit,
    onMedicalHistoryClick: () -> Unit,
    onICUClick: () -> Unit,
    onSettingClick: () -> Unit,
    onBloodFindClick: () -> Unit,
    onBloodDonateClick: () -> Unit,
    onRoutineClick: () -> Unit,
    onSecurityClick: () -> Unit,
    onDevopsClick: () -> Unit,
    onReportClick: () -> Unit,
    onSignOut: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp, 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        NavigationBarIcon(
            title = "Maintain Database", icon = painterResource(id = R.drawable.server)
        ) {
            onMaintainClick()
        }
        NavigationBarIcon(
            title = "Questions and Answers", icon = painterResource(id = R.drawable.qna)
        ) {
            onQNAClick()
        }
        NavigationBarIcon(
            title = "Profile", icon = painterResource(id = R.drawable.profile)
        ) {
            onProfileClick()
        }
        NavigationBarIcon(
            title = "Medical history",
            icon = painterResource(id = R.drawable.medical_history)
        ) {
            onMedicalHistoryClick()
        }
        NavigationBarIcon(
            title = "Check ICU",
            icon = painterResource(id = R.drawable.medical_history)
        ) {
            onICUClick()
        }

        NavigationBarIcon(
            title = "Settings", icon = painterResource(id = R.drawable.settings)
        ) {
            onSettingClick()
        }
        NavigationBarIcon(
            title = "Find Blood", icon = painterResource(id = R.drawable.find_blood)
        ) {
            onBloodFindClick()
        }
        NavigationBarIcon(
            title = "Donate Blood", icon = painterResource(id = R.drawable.donate_blood)
        ) {
            onBloodDonateClick()
        }
        NavigationBarIcon(
            title = "Routine check up",
            icon = painterResource(id = R.drawable.routine_checkup)
        ) {
            onRoutineClick()
        }

        NavigationBarIcon(
            title = "Security", icon = painterResource(id = R.drawable.security)
        ) {
            onSecurityClick()
        }
        NavigationBarIcon(
            title = "Developer options", icon = painterResource(id = R.drawable.dev_ops)
        ) {
            onDevopsClick()
        }
        NavigationBarIcon(
            title = "Report bug", icon = painterResource(id = R.drawable.report_bug)
        ) {
            onReportClick()
        }
        NavigationBarIcon(
            title = "Sign Out", icon = painterResource(id = R.drawable.report_bug), color = Color.Red
        ) {
            onSignOut()
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


@Preview
@Composable
private fun HDgf() {

}