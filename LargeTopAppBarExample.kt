package com.example.jetpackcomposecomponents

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun LargeTopAppBarExample(context: Context){

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    var isDrawerOpen by remember {
        mutableStateOf(false)
    }

    if (isDrawerOpen){
        ModalNavigationDrawer(drawerContent = {
            Text("Drawer title", modifier = Modifier.padding(16.dp))
            Divider()
            NavigationDrawerItem(
                label = { Text(text = "Drawer Item") },
                selected = false,
                onClick = { /*TODO*/ }
            )
        }) {

        }
    }
    
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    
    if (showBottomSheet){
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            Text(text = "This is the content.")
            Text(text = "This is the content.")
            Text(text = "This is the content.")
            Text(text = "This is the content.")
            Text(text = "This is the content.")
            Text(text = "This is the content.")
            Text(text = "This is the content.")
            Text(text = "This is the content.")
            Text(text = "This is the content.")

        }
    }
    
    var sliderPosition by remember {
        mutableStateOf(0f)
    }
    var sliderAdvancePosition by remember {
        mutableStateOf(0f)
    }
    var rangeSliderPosition by remember {
        mutableStateOf(0f..100f)
    }
    
    var checked by remember {
        mutableStateOf(true)
    }
    var advanceSwitchCheck by remember {
        mutableStateOf(true)
    }

    val resource = Uri.parse("android.resource://${context.packageName}/${R.drawable.solarsystem}")
    val painter = rememberAsyncImagePainter(resource)

    var selected by remember { mutableStateOf(false) }
    var enabled by remember {
        mutableStateOf(true)
    }
    if (!enabled) return

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val openAlertDialogBox = remember {
        mutableStateOf(false)
    }
    var openMinimumDialogBox by remember {
        mutableStateOf(false)
    }
    var openAdvanceDialogBox by remember {
        mutableStateOf(false)
    }

    if (openMinimumDialogBox){
        SimpleDialogBox {
            openMinimumDialogBox = false
        }
    }

    if (openAlertDialogBox.value){
        AlertDialogBox(isDialogOpen = openAlertDialogBox, dialogTitle = "Alert dialog example", dialogText = "This is an example of an alert dialog with buttons.", icon = Icons.Default.Info)
    }
    
    if (openAdvanceDialogBox){
        AdvanceDialogBox(onDismissRequest = { openAdvanceDialogBox = false }, painter = painter )
    }

    Scaffold (
        snackbarHost = { SnackbarHost (hostState = snackbarHostState)},
        topBar = {
            LargeTopAppBar(
                title = { 
                    Text(
                        text = "Large Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        scope.launch{
                            showBottomSheet = true
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            BottomAppBar(
                actions ={
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Check, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = null)
                    }
                } ,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            scope.launch {
                                val result = snackbarHostState
                                    .showSnackbar(
                                        message = "Hey, This is my first snackbar.",
                                        actionLabel = "Action",
                                        duration = SnackbarDuration.Indefinite
                                    )
                                when(result){
                                    SnackbarResult.ActionPerformed ->{
                                        Toast.makeText(context,"The action is performed",Toast.LENGTH_SHORT).show()
                                    }
                                    SnackbarResult.Dismissed ->{
                                        Toast.makeText(context,"You dismissed the snackbar.",Toast.LENGTH_SHORT).show()

                                    }
                                }
                            }
                                  },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                    }
                }
            )
        }
    ){
            innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Text(modifier = Modifier.padding(4.dp), text = "Types of Buttons", fontWeight = FontWeight.ExtraBold)
            }
//            Spacer(modifier = Modifier.height(4.dp))
            item {
                Box(modifier = Modifier
                    .wrapContentSize()
                    .width(400.dp)
                    .height(200.dp)
                    .padding(16.dp)
                    .background(Color.LightGray)){
                    FlowRow(modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)

                    ){
                        Button(modifier = Modifier.padding(4.dp),
                            onClick = { /*TODO*/ }) {
                            Text(text = "Filled")
                        }
                        FilledTonalButton(modifier = Modifier.padding(4.dp),onClick = { /*TODO*/ }) {
                            Text(text = "Tonal")
                        }
                        OutlinedButton(modifier = Modifier.padding(4.dp),onClick = { /*TODO*/ }) {
                            Text(text = "Outlined")
                        }
                        ElevatedButton(modifier = Modifier.padding(4.dp),onClick = { /*TODO*/ }) {
                            Text(text = "Elevated")
                        }
                        TextButton(modifier = Modifier.padding(4.dp),onClick = { /*TODO*/ }) {
                            Text(text = "Text Button")
                        }
                    }
                }
            }

            item {
                Text(modifier = Modifier.padding(4.dp), text = "Types of Floating Action Buttons", fontWeight = FontWeight.ExtraBold)

            }
            item {
                Box(modifier = Modifier
                    .wrapContentSize()
                    .width(400.dp)
                    .height(220.dp)
                    .padding(16.dp)
                    .background(Color.LightGray)) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)

                    ) {
                        FloatingActionButton(modifier = Modifier.padding(4.dp), onClick = { /*TODO*/ }) {

                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)

                        }
                        SmallFloatingActionButton(modifier = Modifier.padding(4.dp), onClick = { /*TODO*/ }) {

                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)

                        }
                        LargeFloatingActionButton(modifier = Modifier.padding(4.dp), onClick = { /*TODO*/ }, shape = CircleShape) {

                            Icon(imageVector = Icons.Filled.Add, contentDescription = null)

                        }
                        ExtendedFloatingActionButton(
                            onClick = {  },
                            icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
                            text = { Text(text = "Extended FAB") },
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))

            }
            item { 
                Card() {
                    Text(text = "Hello, world!")
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))

            }
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .size(width = 240.dp, height = 100.dp)
                ) {
                    Text(
                        text = "Filled",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )

                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

            }
            item {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 16.dp
                    ),
                    modifier = Modifier
                        .size(width = 240.dp, height = 100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray
                    )
                ) {
                    Text(
                        text = "Elevated",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))

            }
            item {
                OutlinedCard (
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .size(width = 240.dp, height = 100.dp)
                        .padding(16.dp)
                ){
                    Text(
                        text = "Outlined",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .width(400.dp)
                        .height(220.dp)
                        .padding(16.dp)
                        .background(Color.LightGray)
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)

                    ) {
                        AssistChip(
                            onClick = { Toast.makeText(context,"Assist chip clicked", Toast.LENGTH_SHORT).show() },
                            label = { Text(text = "Assist Chip") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Settings,
                                    contentDescription = null,
                                    Modifier.size(AssistChipDefaults.IconSize)
                                )
                            }
                        )

                        FilterChip(
                            selected = selected,
                            onClick = { selected = !selected },
                            label = { Text(text = "Filter Child") },
                            leadingIcon = {if (selected){
                                Icon(imageVector = Icons.Filled.Done, contentDescription = null)
                            }else{
                                null
                            }
                            }
                            )
                        
                        InputChip(
                            selected = enabled,
                            onClick = { enabled = !enabled },
                            label = { Text(text = "Input Chip") },
                            avatar = {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = null,
                                    Modifier.size(InputChipDefaults.AvatarSize)
                                )
                            },
                            trailingIcon = {
                                Icon(imageVector = Icons.Filled.Close, contentDescription = null, Modifier.size(InputChipDefaults.AvatarSize))
                            }
                        )

                    }

                }
            }

            item {
                Box(modifier = Modifier
                    .wrapContentSize()
                    .width(400.dp)
                    .height(170.dp)
                    .padding(16.dp)
                    .background(Color.LightGray)) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)

                    ) {

                        Button(onClick = {
                                         openAlertDialogBox.value = true
                                         }, modifier = Modifier.padding(4.dp)) {
                            Text(text = "Alert Dialog Box")
                        }
                        Button(onClick = { openMinimumDialogBox = true }, modifier = Modifier.padding(4.dp)) {
                            Text(text = "Simple Dialog Box")
                        }
                        Button(onClick = { openAdvanceDialogBox = true }, modifier = Modifier.padding(4.dp)) {
                            Text(text = "Advance Dialog Box")
                        }

                    }
                }
            }
            item {
                Box(modifier = Modifier
                    .wrapContentSize()
                    .width(400.dp)
                    .height(300.dp)
                    .padding(16.dp)
                    .background(Color.LightGray)) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)

                    ) {
                        LinearDeterminateIndicator()
                        CircularDeterminateIndicator()
                        IndeterminateCircularIndicator()
                        IndeterminateLinearIndicator()

                    }
                }
            }
            
            item {
                Box(modifier = Modifier
                    .wrapContentSize()
                    .width(400.dp)
                    .height(270.dp)
                    .padding(16.dp)
                    .background(Color.LightGray)) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)

                    ){
                        Slider(value = sliderPosition, onValueChange = {sliderPosition = it})
                        Text(text = sliderPosition.toString())
                        Slider(
                            value = sliderAdvancePosition,
                            onValueChange = { sliderAdvancePosition = it },
                            colors = SliderDefaults.colors(
                                thumbColor = Color.Red,
                                activeTrackColor = Color.Cyan,
                                inactiveTrackColor = Color.Green
                            ),
                            steps = 3,
                            valueRange = 0f..50f
                        )
                        Text(text = sliderAdvancePosition.toString())
                        
                        RangeSlider(
                            value = rangeSliderPosition,
                            onValueChange = {rangeSliderPosition = it},
                            steps = 5,
                            valueRange = 0f..100f,
                            onValueChangeFinished = {}
                        )
                        Text(text = rangeSliderPosition.toString())
                    }
                }
            }
            item {
                Box(modifier = Modifier
                    .wrapContentSize()
                    .width(400.dp)
                    .height(100.dp)
                    .padding(16.dp)
                    .background(Color.LightGray)) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)

                    ) {
                        
                        Switch(checked = checked, onCheckedChange = {checked = it})
                        Switch(
                            modifier = Modifier.padding(4.dp),
                            checked = advanceSwitchCheck,
                            onCheckedChange = {advanceSwitchCheck = it},
                            thumbContent = {
                                if (advanceSwitchCheck){
                                    Icon(
                                        imageVector = Icons.Filled.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(SwitchDefaults.IconSize)
                                    )
                                } else{
                                    null
                                }
                            }
                        )

                    }
                }
            }

            item {
                Box(modifier = Modifier
                    .wrapContentSize()
                    .width(400.dp)
                    .height(100.dp)
                    .padding(16.dp)
                    .background(Color.LightGray)) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)

                    ) {
                        CheckboxMinimalExample()

                    }
                }
            }
            
        }
    }
}