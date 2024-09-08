package com.example.medai.db

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class MedicineObject() : RealmObject  {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var group: String = ""
    var price: Int = 0
    var usage: String = ""
    var dosage: String = ""
    var dosageTime: String = ""
    var advice: String = "Take with consultant of a professional doctors"
    var precautions: String = "Do not provide to children below 16 years"
    var prerequisiteMeds: String? = null
    var image: Int? = null
    var description: String? = null
    var manufacturer: String? = null
    var supplier: String? = null

}
