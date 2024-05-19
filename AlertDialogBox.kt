package com.example.jetpackcomposecomponents

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AlertDialogBox(
    isDialogOpen: MutableState<Boolean>,
    dialogTitle: String,
    dialogText : String,
    icon : ImageVector
){
    AlertDialog(
        onDismissRequest = { isDialogOpen.value = false },
        confirmButton = {
            TextButton(onClick = { isDialogOpen.value = false }) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = { isDialogOpen.value = false }) {
                Text(text = "Cancel")
            }
        },
        icon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        }
    )
}