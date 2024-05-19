package com.example.jetpackcomposecomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircularDeterminateIndicator(){

    var currentProgress by remember {
        mutableStateOf(0f)
    }
    var loading by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            loading = true
            scope.launch {
                loadCircularProgress {
                        progress->
                    currentProgress = progress
                }
                loading = false
            }
        }, enabled = !loading) {
            Text(text = "Start Loading Circular")
        }
        if (loading){
            CircularProgressIndicator(progress = currentProgress, modifier = Modifier.wrapContentSize().size(50.dp))
        }
    }

}

suspend fun loadCircularProgress(updateProgress: (Float) -> Unit){
    for (i in 1..100){
        updateProgress(i.toFloat() / 100)
        delay(100L)
    }
}