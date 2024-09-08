package com.example.medai.db

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.medai.R


enum class Screens {
    MainScreen, OrderScreen, CartScreen,
    RecordActivitiesScreen, BookAppointmentScreen, SearchProductsScreen, BookLabTestScreen,
    AdminHomePage, SignUp, LogIn
}


val volkorn = FontFamily(
    Font(R.font.volkorn, weight = FontWeight.Normal)
)

// article dummy
class Article(
    val title: String,
    val description: String,
    val author: String,
    val view: Int,
    val rating: Double,
    val image: Int?
)


val articleList = listOf(
    Article(
        "Covid-19. How to keep your family safe!",
        "To keep your fa To keep your family safe from Covid-19, follow key precautions: encourage frequent hand washing with soap and water for at least 20 seconds, or use hand sanitizer with at least 60% alcohol. Wear masks in crowded or enclosed spaces, especially if someone is sick or at higher risk. Practice physical distancing, aiming for at least 6 feet apart from others. Clean and disinfect frequently touched surfaces daily. Stay updated on local mily safe from Covid-19, follow key precautions: encourage frequent hand washing with soap and water for at least 20 seconds, or use hand sanitizer with at least 60% alcohol. Wear masks in crowded or enclosed spaces, especially if someone is sick or at higher risk. Practice physical distancing, aiming for at least 6 feet apart from others. Clean and disinfect frequently touched surfaces daily. Stay updated on local",
        "Redwan Hussain",
        1234,
        3.5,
        R.drawable.demo
    ), Article(
        "Why mental health is important for us!",
        "To keep your fa To keep your family safe from Covid-19, follow key precautions: encourage frequent hand washing with soap and water for at least 20 seconds, or use hand sanitizer with at least 60% alcohol. Wear masks in crowded or enclosed spaces, especially if someone is sick or at higher risk. Practice physical distancing, aiming for at least 6 feet apart from others. Clean and disinfect frequently touched surfaces daily. Stay updated on local mily safe from Covid-19, follow key precautions: encourage frequent hand washing with soap and water for at least 20 seconds, or use hand sanitizer with at least 60% alcohol. Wear masks in crowded or enclosed spaces, especially if someone is sick or at higher risk. Practice physical distancing, aiming for at least 6 feet apart from others. Clean and disinfect frequently touched surfaces daily. Stay updated on local",
        "Redwan Hussain",
        1234,
        3.5,
        null
    )
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
    ),
    Medicine(
        name = "Seclo 40",
        group = "Omeprazole",
        price = 60,
        usage = "gastric related problems",
        dosage = "1-1-1",
        dosageTime = "30 min before eating"
    ),
    Medicine(
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
    Doctors("Dr. najmul islam foga", "Sex and Disease", "150", R.drawable.demo),
    Doctors("Dr. Miner Rimon", "Gayene", "180", R.drawable.demo),
    Doctors("Dr. Ashfak Uzza", "Orthopedicks", "90", R.drawable.demo),
)

