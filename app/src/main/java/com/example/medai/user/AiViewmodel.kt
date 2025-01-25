package com.example.medai.user

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import kotlinx.coroutines.launch

class AiViewmodel : ViewModel() {


    var isLoading = mutableStateOf(false)
    var prompt = mutableStateOf("")
    var answer = mutableStateOf("")

    private val model = GenerativeModel(
        modelName = "gemini-1.5-flash-001",
        apiKey = "AIzaSyDmj-_O5pWY_yytCR6HrTO4_XdLGyWXoYE",
        safetySettings = listOf(
            SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE),
            SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.MEDIUM_AND_ABOVE),
            SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.MEDIUM_AND_ABOVE),
            SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.MEDIUM_AND_ABOVE)
        )
    )


    fun getAnswer(prompt: String) {
        viewModelScope.launch {
            isLoading.value = true
            var response = model.generateContent(prompt)
            answer.value = response.text ?: "No response"
            isLoading.value = false
        }
    }


}