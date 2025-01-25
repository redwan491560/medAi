package com.example.medai.viewmodels.item

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medai.util.Routes
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MedicineViewmodel : ViewModel() {


    private val medsRef = FirebaseDatabase.getInstance().getReference("medicine")


    private val _medicineList = MutableStateFlow<List<Routes.MedicineViewer>>(emptyList())
    val medicineList = _medicineList


    var medicineItem = MutableStateFlow<Routes.MedicineViewer?>(null)
        private set


    private val _searchMedicineList = MutableStateFlow<List<Routes.MedicineViewer>>(emptyList())
    val searchMedicineList = _searchMedicineList


    fun createMedicine(
        name: String,
        group: String,
        price: String,
        usage: String = "",
        dosage: String = "",
        dosageTime: String = "",
        advice: String = "",
        precautions: String = "",
        prerequisiteMeds: String = "",
        description: String = "",
        manufacturer: String = "",
        supplier: String = "",
        onClick: () -> Unit
    ) {

        if (name.isEmpty() || group.isEmpty() || price.isEmpty()) return

        viewModelScope.launch {

//            val uid = "med" + medsRef.push().key
            val uid = medsRef.push().key ?: return@launch

            val medsData = Routes.MedicineViewer(
                uid = uid,
                name = name,
                group = group,
                price = price.toInt(),
                usage = usage,
                dosage = dosage,
                dosageTime = dosageTime,
                advice = advice,
                precautions = precautions,
                prerequisiteMeds = prerequisiteMeds,
                description = description,
                manufacturer = manufacturer,
                supplier = supplier
            )

            medsRef.child(group).child(uid).setValue(medsData).addOnSuccessListener {
                onClick()
                Log.d("success", "createMedicine: ")
            }.addOnFailureListener {
                Log.d("failed", "createMedicine: ")
            }
        }
    }


    fun readAllMedicines() {

        viewModelScope.launch {
            medsRef.get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val medsList = mutableListOf<Routes.MedicineViewer>()
                    dataSnapshot.children.forEach { groupSnapshot ->
                        groupSnapshot.children.forEach { medicineSnapshot ->
                            val medicine =
                                medicineSnapshot.getValue(Routes.MedicineViewer::class.java)
                            medicine?.let {
                                medsList.add(it)
                            }
                        }
                    }
                    _medicineList.value = medsList
                    Log.d("success", "readAllMedicines: Medicines retrieved successfully.")
                } else {
                    _medicineList.value = emptyList()
                    Log.d("empty", "readAllMedicines: No data found.")
                }
            }.addOnFailureListener { exception ->
                _medicineList.value = emptyList()
                Log.e("error", "readAllMedicines: ${exception.message}")
            }
        }
    }


    // delete medicine

    fun deleteMedicineById(uid: String) {
        if (uid.isEmpty()) {
            Log.d("error", "deleteMedicineByName: UID is empty.")
            return
        }

        viewModelScope.launch {
            medsRef.get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {

                    // Search through groups for the medicine with the given UID
                    dataSnapshot.children.forEach { groupSnapshot ->
                        if (groupSnapshot.hasChild(uid)) {
                            groupSnapshot.child(uid).ref.removeValue().addOnSuccessListener {
                                Log.d(
                                    "success",
                                    "deleteMedicineByName: Medicine with UID $uid deleted."
                                )
                            }.addOnFailureListener { exception ->
                                Log.e("error", "deleteMedicineByName: ${exception.message}")
                            }
                            return@addOnSuccessListener
                        }
                    }

                } else {
                    Log.d("error", "deleteMedicineByName: Database is empty.")
                }
            }.addOnFailureListener { exception ->
                Log.e("error", "deleteMedicineByName: ${exception.message}")
            }
        }
    }


    // update medicine


    // view all medicine

    fun searchMedicineByName(name: String, onResult: (List<Routes.MedicineViewer>) -> Unit) {

        if (name.isEmpty()) {
            Log.d("error", "searchMedicineByName: Name is empty.")
            return
        }

        viewModelScope.launch {
            medsRef.get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    val resultList = mutableListOf<Routes.MedicineViewer>()
                    dataSnapshot.children.forEach { groupSnapshot ->
                        groupSnapshot.children.forEach { medicineSnapshot ->
                            val medicine =
                                medicineSnapshot.getValue(Routes.MedicineViewer::class.java)
                            if (medicine != null && medicine.name.equals(name, ignoreCase = true)) {
                                resultList.add(medicine)
                            }
                        }
                    }

                    if (resultList.isNotEmpty()) {
                        onResult(resultList)
                        _searchMedicineList.value = resultList
                    } else {
                        Log.d(
                            "empty",
                            "searchMedicineByName: No medicines found with the name $name."
                        )
                        _searchMedicineList.value = emptyList()
                        onResult(emptyList())
                    }
                } else {
                    _searchMedicineList.value = emptyList()
                    Log.d("empty", "searchMedicineByName: No data found in the database.")
                    onResult(emptyList())
                }
            }.addOnFailureListener { exception ->
                _searchMedicineList.value = emptyList()
                Log.e("error", "searchMedicineByName: ${exception.message}")
                onResult(emptyList())
            }
        }
    }


    fun searchMedicinesByGroup(group: String, onResult: (List<Routes.MedicineViewer>) -> Unit) {
        if (group.isEmpty()) {
            Log.d("error", "searchMedicinesByGroup: Group is empty.")
            return
        }

        viewModelScope.launch {
            val resultList = mutableListOf<Routes.MedicineViewer>()

            medsRef.child(group).get().addOnSuccessListener { groupSnapshot ->
                if (groupSnapshot.exists()) {
                    groupSnapshot.children.forEach { medicineSnapshot ->
                        val medicine = medicineSnapshot.getValue(Routes.MedicineViewer::class.java)
                        medicine?.let {
                            resultList.add(it)
                        }
                    }

                    if (resultList.isNotEmpty()) {
                        onResult(resultList)
                        Log.d(
                            "success",
                            "searchMedicinesByGroup: Found ${resultList.size} medicines in group $group."
                        )
                    } else {
                        Log.d(
                            "empty",
                            "searchMedicinesByGroup: No medicines found in group $group."
                        )
                        onResult(emptyList())
                    }
                } else {
                    Log.d(
                        "empty",
                        "searchMedicinesByGroup: Group $group does not exist in the database."
                    )
                    onResult(emptyList())
                }
            }.addOnFailureListener { exception ->
                Log.e("error", "searchMedicinesByGroup: ${exception.message}")
                onResult(emptyList())
            }
        }
    }


    // view single
    fun searchMedicineById(id: String, onResult: (Routes.MedicineViewer?) -> Unit) {
        if (id.isEmpty()) {
            Log.d("error", "searchMedicineById: ID is empty.")
            return
        }

        viewModelScope.launch {
            medsRef.get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    dataSnapshot.children.forEach { groupSnapshot ->
                        groupSnapshot.children.forEach { medicineSnapshot ->
                            val medicine =
                                medicineSnapshot.getValue(Routes.MedicineViewer::class.java)
                            if (medicine?.uid == id) {
                                onResult(medicine) // Return immediately when medicine is found
                                Log.d("success", "searchMedicineById: Medicine found with ID $id.")
                                return@addOnSuccessListener
                            }
                        }
                    }

                    Log.d("empty", "searchMedicineById: No medicine found with ID $id.")
                    onResult(null)
                } else {
                    Log.d("empty", "searchMedicineById: Database is empty.")
                    onResult(null)
                }
            }.addOnFailureListener { exception ->
                Log.e("error", "searchMedicineById: ${exception.message}")
                onResult(null)
            }
        }
    }


    fun updateMedicineById(
        uid: String,
        updates: Map<String, Any>,
        onResult: (Boolean) -> Unit
    ) {
        if (uid.isEmpty()) {
            Log.d("error", "updateMedicineById: UID is empty.")
            onResult(false)
            return
        }

        viewModelScope.launch {
            medsRef.get().addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot.exists()) {
                    dataSnapshot.children.forEach { groupSnapshot ->
                        groupSnapshot.children.forEach { medicineSnapshot ->
                            val medicine =
                                medicineSnapshot.getValue(Routes.MedicineViewer::class.java)
                            if (medicine?.uid == uid) {
                                val group = groupSnapshot.key // Get the group key dynamically
                                if (group != null) {
                                    medsRef.child(group).child(uid).updateChildren(updates)
                                        .addOnSuccessListener {
                                            Log.d(
                                                "success",
                                                "updateMedicineById: Medicine updated successfully."
                                            )
                                            onResult(true)
                                        }.addOnFailureListener { exception ->
                                            Log.e(
                                                "error",
                                                "updateMedicineById: ${exception.message}"
                                            )
                                            onResult(false)
                                        }
                                }
                                return@addOnSuccessListener
                            }
                        }
                    }

                    Log.d("empty", "updateMedicineById: No medicine found with UID $uid.")
                    onResult(false)
                } else {
                    Log.d("empty", "updateMedicineById: Database is empty.")
                    onResult(false)
                }
            }.addOnFailureListener { exception ->
                Log.e("error", "updateMedicineById: ${exception.message}")
                onResult(false)
            }
        }
    }


    fun createUpdateMap(
        name: String,
        price: String,
        usage: String,
        dosage: String,
        dosageTime: String
    ): Map<String, Any> {
        val updates = mutableMapOf<String, Any>()

        // Add non-empty fields to the map
        if (name.isNotEmpty()) updates["name"] = name
        if (price.isNotEmpty()) {
            val priceValue = price.toIntOrNull()
            if (priceValue != null) {
                updates["price"] = priceValue
            } else {
                Log.d("error", "createUpdateMap: Invalid price input.")
            }
        }
        if (usage.isNotEmpty()) updates["usage"] = usage
        if (dosage.isNotEmpty()) updates["dosage"] = dosage
        if (dosageTime.isNotEmpty()) updates["dosageTime"] = dosageTime

        return updates
    }

}