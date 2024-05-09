package com.example.jetpackcomposepractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpackcomposepractice.ui.theme.JetpackComposePracticeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Counter()
                }
            }
        }
    }
}

@Composable
fun TextBlock (
    count:Int,
    isColorChange:Boolean,
    textSize : TextUnit
){

    Text(
        text = "Counter is : $count",
        color =
        if (isColorChange){
            Color.Cyan
        }else{
            Color.Red
        },
        fontSize = textSize,
        

    )
}

@Composable
fun Counter(){

    var tryFlow : Flow<Int> = flowOf(0)

    var collectedFlow by remember {
        mutableStateOf(flowOf(0))
    }

    var rotatedFlow by remember {
        mutableStateOf(flowOf(0))
    }

    var count by remember {
        mutableStateOf(0)
    }
    var isColorChange by remember {
        mutableStateOf(false)
    }

    var toLaunch by remember {
        mutableStateOf(true)
    }

    if (count % 2 == 0){
        isColorChange = true
        toLaunch = false
    }else{
        isColorChange = false
        toLaunch = true
    }

    var textSize by remember {
        mutableStateOf(60.sp)
    }


    LaunchedEffect(toLaunch){
        if (toLaunch){
            Log.d("CounterResponse","The Launch effect is running for counter : $count")
            if (count % 2 != 0) {
                tryFlow = flow {
                    if (count >= 0){
                        for (i in 1..count) {
                            emit(i)
                            delay(1000L)
                        }
                    }else{
                        for (i in count..0){
                            emit(i*-1)
                            delay(1000L)
                        }
                    }
                }
                tryFlow.collect {
                    collectedFlow = flowOf(it)
                    textSize = (it*10).sp
                }
            } else {
                // Reset stateFlow if count is even
                collectedFlow = flowOf(0)
            }
        }

    }
    val stateFlow = collectedFlow.collectAsStateWithLifecycle(initialValue = flowOf(0))
    val rotatedStateFlow = rotatedFlow.collectAsStateWithLifecycle(initialValue = flowOf(0))
    

    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(2.dp, Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (!isColorChange){
            TextBlock(count, isColorChange, textSize)
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(onClick = {
                count++
                textSize = 60.sp



            }) {
                Text(text = "Increase")
            }
            Button(onClick = { count--
                textSize = 20.sp

            }) {
                Text(text = "Decrease")
                
            }
        }
        if (isColorChange){
            TextBlock(count, isColorChange, textSize)
        }
        
        Text(text = stateFlow.value.toString())

    }

    
}

//Using remember function as logic builder
@Composable
fun LogicBuilder(){
    var name by remember {
        mutableStateOf("")
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (name.isNotEmpty()){
            Text(text = "Hello ! $name")
        }else{
            Text(text = "There is no name to show")
        }

        OutlinedTextField(value = name, onValueChange = {name = it})
    }
}