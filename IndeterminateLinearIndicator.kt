package com.example.jetpackcomposecomponents

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun IndeterminateLinearIndicator(){
    var loading by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            scope.launch {
                loading = true
                delay(5000L)
                loading = false
            }
        },
        enabled = !loading
    ) {
        Text(text = "Start loading")
    }

    if (!loading) return

    LinearProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )

}