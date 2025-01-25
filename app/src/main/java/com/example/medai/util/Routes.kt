package com.example.medai.util

import kotlinx.serialization.Serializable

@Serializable
class Routes {


    @Serializable
    object Splash

     @Serializable
    object CheckAdmin

    @Serializable
    object Login

    @Serializable
    object Register


    // screens
    @Serializable
    object Main


    @Serializable
    object Chat


    @Serializable
    object Cart

    @Serializable
    object RecordActivities

    @Serializable
    object BookAppointmentScreen

    @Serializable
    object SearchProductsScreen

    @Serializable
    object BookLabTestScreen

    @Serializable
    object DoctorHomePage

    @Serializable
    object ManageAppointmentScreen

    @Serializable
    object ManageScheduleScreen

    @Serializable
    object UploadArticleScreen

    @Serializable
    object AiScreen

    @Serializable
    data class UserModel(
        val uid: String = "",
        val email: String = "",
        val username: String = ""
    )


    @Serializable
    data class ArticleViewer(
        var uid: String = "",
        var title: String = "",
        var description: String = "",
        var author: String = "",
        var story: String = "",
        val view: Int = 0,
        val rating: Double = 0.0,
        val image: Int = 0,
        val timeStamp: Long = 0L,
        var category: String = ""
    )


    @Serializable
    data class MedicineViewer(
        val uid: String = "",
        val name: String = "",
        val group: String = "",
        val price: Int = 0,
        val usage: String = "",
        val dosage: String = "",
        val dosageTime: String = "",
        val advice: String = "Take with consultant of a professional doctors",
        val precautions: String = "Do not provide to children below 16 years",
        val prerequisiteMeds: String? = null,
        val image: Int = 0,
        val description: String = "",
        val manufacturer: String = "",
        val supplier: String = ""
    )

    data class NewsItem(
        val title: String = "",
        val author: String = "",
        val timestamp: Long = 0L,
        val description: String = "",
        val story: String = "",
        val category: String = "",
    )


    // crud
    @Serializable
    object ArticleCrud

    @Serializable
    object MedicineCrud

    @Serializable
    object ViewArticle

    @Serializable
    object ViewMedicine

    @Serializable
    object CRUD


    // nav bars
    @Serializable
    object Personalize

    @Serializable
    object Accounts

    @Serializable
    object ReportBug

    @Serializable
    object Security

    @Serializable
    object Setting

    @Serializable
    object Developers
}
