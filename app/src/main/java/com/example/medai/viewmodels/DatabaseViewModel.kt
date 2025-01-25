package com.example.medai.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.medai.R
import com.example.medai.db.Appointment
import com.example.medai.db.Doctors
import com.example.medai.db.Medicine
import com.example.medai.db.NewsItem
import com.example.medai.util.Routes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class DatabaseViewModel : ViewModel() {


    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    val newsList = mutableStateOf<List<NewsItem>>(emptyList())
    val isLoading = mutableStateOf(true)

    // function to fetch news from the collection
    fun fetchNews() {
        val user = auth.currentUser
        if (user != null) {
            firestore.collection("news").get()
                .addOnSuccessListener { result ->
                    val newsItems = mutableListOf<NewsItem>()
                    for (document in result) {
                        val title = document.getString("title") ?: ""
                        val author = document.getString("author") ?: ""
                        val timestamp = document.getString("timestamp") ?: ""
                        val description = document.getString("description") ?: ""

                        newsItems.add(NewsItem(title, author, timestamp, description))
                    }
                    newsList.value = newsItems
                    isLoading.value = false
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Error fetching documents: $exception")
                    isLoading.value = false
                }
        } else {
            Log.e("Auth", "User is not authenticated")
            isLoading.value = false
        }
    }


    // signup states
    val typesOfUser = listOf("User", "Doctor", "Owner", "Sales")
    val userType = mutableStateOf("Select User Type")

    // user in general
    val userName = mutableStateOf("")


    // user doctor
    val bmdcLicense = mutableStateOf("")

    // owner
    val shopName = mutableStateOf("")
    val shopLocation = mutableStateOf("")

    // sales
    val c_ID = mutableStateOf("")
    val companyName = mutableStateOf("")


    val medicineListUser = listOf(
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
    val doctorList_user = emptyList<Doctors>()

    var appointmentList = mutableListOf(
        Appointment(
            serial = 0, name = "Redwan", contact = 124578
        ), Appointment(
            serial = 0, name = "Redwan", contact = 124578
        ), Appointment(
            serial = 0, name = "Redwan", contact = 124578
        ), Appointment(
            serial = 0, name = "Redwan", contact = 124587
        )
    )
}
