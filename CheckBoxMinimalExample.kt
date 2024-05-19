package com.example.jetpackcomposecomponents

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Composable
fun CheckboxMinimalExample(){
    var checked by remember {
        mutableStateOf(true)
    }

    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "Minimal checkbox")
        Checkbox(checked = checked, onCheckedChange = {checked = it})
    }
    if (checked){
        Text(text = "Checkbox is checked.")
    }else{
        "Checkbox is unchecked."
    }
}