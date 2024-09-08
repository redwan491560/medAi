package com.example.medai.viewmodels

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var artState = mutableIntStateOf(0)
    var medState = mutableIntStateOf(0)
    var docState = mutableIntStateOf(0)


    val location = mutableStateOf("")
    val hospital = mutableStateOf("")
    val department = mutableStateOf("")
    val doctor = mutableStateOf("")
    val time = mutableStateOf("")

    val timeList = listOf("time1", "time2", "time3")
    val doctorsList = listOf("doc1", "doc2", "doc3")
    val locationList = listOf("loc1", "loc2", "loc3")
    val hospitalList = listOf("hos1", "hos2", "hos3")
    val departmentList = listOf("dept1", "dept2", "dept3")


}