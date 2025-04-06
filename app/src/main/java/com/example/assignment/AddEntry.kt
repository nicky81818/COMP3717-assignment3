package com.example.assignment

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.assignment.data.Entry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(navController: NavController){
    val entriesState: EntriesState = viewModel(LocalActivity.current as ComponentActivity)
    Box(modifier = Modifier.safeDrawingPadding()){
        var modalDismiss by remember{ mutableStateOf(true)}
        if (!modalDismiss) {
            BasicAlertDialog(
                onDismissRequest = {
                    modalDismiss = true
                    navController.navigate("home")
                                   },
                content = { // TODO: MAKE PRETTIER
                    Column(modifier = Modifier.background(Color.White)) {
                        Text("Move history has been printed!")
                        Button(onClick = {
                            modalDismiss = true
                            navController.navigate("home")
                        }) {
                            Text("Close")
                        }
                    }
                }
            )
        }

        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            val addEntryState = remember { AddEntryState() }

            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text("New Entry: ${addEntryState.readableDate}",
                    fontSize = 50.sp,
                    modifier = Modifier.padding(10.dp))

                Spacer(modifier = Modifier.height(10.dp))

                MyTextField(
                    addEntryState.summary,
                    addEntryState.onSummaryChanged,
                    label = addEntryState.summaryLabel
                )

            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    entriesState.add(
                        Entry(date = addEntryState.date,
                            summary = addEntryState.summary)
                    )
                    entriesState.refresh()
                    modalDismiss = false
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(10.dp)
                ) {
                Text("Add Entry")
            }
        }
    }
}

@Composable
fun MyTextField(
    value: String,
    onValueChanged:(String)->Unit,
    invalid:Boolean = false,
    label: String
){
    Column(modifier = Modifier.padding(20.dp)) {
        Text(label, fontSize = 20.sp)
        TextField(
            value = value,
            onValueChange = onValueChanged,
            textStyle = TextStyle(fontSize = 30.sp),
            isError = invalid
        )
    }
}
