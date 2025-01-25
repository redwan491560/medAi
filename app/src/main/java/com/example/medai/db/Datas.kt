package com.example.medai.db

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.medai.R
import kotlinx.serialization.Serializable



sealed class Status {
    data object Authenticated : Status()
    data object NotAuthenticated : Status()
    data object Loading : Status()
    data class Error(val message: String) : Status()
}

val volkorn = FontFamily(
    Font(R.font.volkorn, weight = FontWeight.Normal)
)

data class NewsItem(
    val title: String = "",
    val author: String = "",
    val timestamp: String = "",
    val description: String = ""
)


//class News(
//    val title: String,
//    val description: String,
//    val author: String,
//    val view: Int,
//    val rating: Double,
//    val image: Int?
//)

class Appointment(
    val name: String,
    val contact: Int,
    val serial: Int,
    val approved: MutableState<Boolean> = mutableStateOf(false)
)

class Article(
    val title: String,
    val description: String,
    val author: String,
    val view: Int,
    val rating: Double,
    val image: Int?,
    val category: String? = null
)

class Medicine(
    val name: String,
    val group: String,
    val price: Int,
    val usage: String,
    val dosage: String,
    val dosageTime: String,
    val advice: String = "Take with consultant of a professional doctors",
    val precautions: String = "Do not provide to children below 16 years",
    val prerequisiteMeds: String? = null,
    val image: Int? = null,
    val description: String? = null,
    val manufacturer: String? = null,
    val supplier: String? = null
)

val listOfMeds = listOf(
    Medicine(
        name = "Seclo 20",
        group = "Omeprazole",
        price = 49,
        usage = "gastric related problems",
        dosage = "1-1-1",
        dosageTime = "30 min before eating"
    ), Medicine(
        name = "Seclo 40",
        group = "Omeprazole",
        price = 60,
        usage = "gastric related problems",
        dosage = "1-1-1",
        dosageTime = "30 min before eating"
    ), Medicine(
        name = "maxpro 20",
        group = "Omeprazole",
        price = 98,
        usage = "gastric related problems",
        dosage = "1-1-1",
        dosageTime = "30 min before eating"
    )
)


class Doctors(
    val name: String, val group: String, val visit: String, val image: Int
)

val listOfDoctors = listOf(
    Doctors(
        "Dr. Redwan Hussain", "Medicine", "500", image = R.drawable.demo
    ),
    Doctors("Dr. Kader", "Sex and Disease", "150", R.drawable.demo),
    Doctors("Dr. Miner Rimon", "Gayene", "180", R.drawable.demo),
    Doctors("Dr. Noakhali", "Orthopedicks", "90", R.drawable.demo),
)

