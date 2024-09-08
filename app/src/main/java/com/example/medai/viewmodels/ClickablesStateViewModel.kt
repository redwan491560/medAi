package com.example.medai.viewmodels

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class ClickableStateViewModel :ViewModel() {
    val aboutState = mutableIntStateOf(1)
    val learnMoreState = mutableIntStateOf(0)
    val newState = mutableIntStateOf(0)
    val popularState = mutableIntStateOf(0)
    val savedState = mutableIntStateOf(0)
    val remoteState = mutableIntStateOf(1)
    val onsiteState = mutableIntStateOf(0)

}