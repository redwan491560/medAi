package com.example.medai.viewmodels

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var artState = mutableIntStateOf(0)
    var medState = mutableIntStateOf(0)
    var docState = mutableIntStateOf(0)


    val location = mutableStateOf("Location")
    val hospital = mutableStateOf("Hospital")
    val department = mutableStateOf("Department")
    val doctor = mutableStateOf("Doctor's name")
    val time = mutableStateOf("Time & date")

    val timeList = listOf(

        "9.00-9.30 AM",
        "9.30-10.00 AM",
        "10.00-10.30 AM",
        "8.30-9.00 AM",
        "8.00-8.30 PM",
        "8.30-9.00 PM",
        "9.00-9.30 PM",
        "9.30-10.00 PM",
        "10.00-10.30 PM",
        "8.30-9.00 PM"
    )
    val doctorsList = listOf(
        "Dr. Redwan Hussain",
        "Dr. Redwan Hussain",
        "Dr. Miner Hossain Rimon",
        "Dr. Nazmul Islam",
        "Dr. Redwan Hussain",
        "Dr. Miner Hossain Rimon",
        "Dr. Nazmul Islam",
        "Dr. Redwan Hussain",
        "Dr. Miner Hossain Rimon",
        "Dr. Nazmul Islam",
        "Dr. Miner Hossain Rimon",
        "Dr. Nazmul Islam"
    )
    val locationList = listOf("Sylhet", "Dhaka", "Chittagong", "Sylhet", "Dhaka", "Chittagong")
    val hospitalList = listOf(
        "Ibn Sina Hospital, Sylhet",
        "Mount Adora Hospital, Akhalia",
        "Sylhet Women's Hospital",
        "Mount Adora Hospital, Akhalia",
        "Sylhet Women's Hospital",
        "Mount Adora Hospital, Akhalia",
        "Sylhet Women's Hospital"
    )
    val departmentList = listOf(
        "Medicine",
        "Dermatology",
        "Heart disease",
        "Medicine",
        "Dermatology",
        "Heart disease",
        "Medicine",
        "Dermatology",
        "Heart disease"
    )


}