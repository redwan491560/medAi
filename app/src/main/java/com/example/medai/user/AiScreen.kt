package com.example.medai.user

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medai.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AiScreen(aiViewmodel: AiViewmodel) {
    Scaffold(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
            .systemBarsPadding(),
        bottomBar = {
            Row {
                OutlinedTextField(
                    value = aiViewmodel.prompt.value,
                    onValueChange = {
                        aiViewmodel.prompt.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(10.dp, 10.dp),
                    placeholder = {
                        Text(text = "Ask MedAi!")
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        aiViewmodel.getAnswer(aiViewmodel.prompt.value)
                    }),
                    trailingIcon = {
                        Image(painter = painterResource(id = R.drawable.baseline_send_24),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable {
                                    if (aiViewmodel.prompt.value.isNotBlank()) {
                                        aiViewmodel.getAnswer(aiViewmodel.prompt.value)
                                    }
                                })

                    })
            }

        }
    ) {
        // contents
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
        ) {
            Column(Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (aiViewmodel.isLoading.value) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else if (aiViewmodel.answer.value.isNotEmpty()) {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Column(
                                Modifier.padding(10.dp)
                            ) {
                                Text(
                                    aiViewmodel.answer.value,
                                    textAlign = TextAlign.Justify, fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card {

                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun AiVis() {
    AiScreen(aiViewmodel = AiViewmodel())
}