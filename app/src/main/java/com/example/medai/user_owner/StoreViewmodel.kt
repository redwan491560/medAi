package com.example.medai.user_owner

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class StoreViewmodel : ViewModel() {
    private val shopName = mutableStateOf("")
    fun getName(): MutableState<String> {
        return shopName
    }

    private val status = mutableStateOf(false)
    fun getStatus(): MutableState<Boolean> {
        return status
    }
}