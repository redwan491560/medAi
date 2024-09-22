package com.example.medai.viewmodels

import androidx.lifecycle.ViewModel
import com.example.medai.db.Appointment
import com.example.medai.db.Article
import com.example.medai.db.Doctors
import com.example.medai.db.Medicine
import com.example.medai.db.News


class DatabaseViewModel : ViewModel() {
    val newsList = emptyList<News>()

    val articleList = emptyList<Article>()
    val medicineList_user = emptyList<Medicine>()
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
