package com.example.quizapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizapp.model.Answer

@Composable
fun RadioButton(radioOptions: List<Answer>) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
    Column {
        radioOptions.forEach { answer ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (answer == selectedOption),
                        onClick = {
                            onOptionSelected(answer)
                        }
                    )
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material.RadioButton(
                    selected = (answer == selectedOption),
                    onClick = { onOptionSelected(answer) }
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = answer.text,
                    style = MaterialTheme.typography.body1.merge(),
                )
            }
        }
    }
}