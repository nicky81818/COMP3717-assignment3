package com.example.assignment

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun Home(quoteState: QuoteState) {
    val entriesState: EntriesState = viewModel(LocalActivity.current as ComponentActivity)
    Column (modifier = Modifier.safeDrawingPadding()){
        val entries = entriesState.entries
        var searchVisible by remember{ mutableStateOf(false )}
        Column {
            if (searchVisible) {
                Row {
                    TextField(
                        entriesState.searchFlow.collectAsState().value,
                        onValueChange = {
                            entriesState.searchFlow.value = it
                            entriesState.search(entriesState.searchFlow.value)
                        })
                    IconButton(onClick = {searchVisible = false}) {Icon(Icons.Default.Close, contentDescription = "Close") }
                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { searchVisible = true }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    Text("Search for an Entry")
                }
            }

            LazyColumn {
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.background(Color(0xFFFCF5E5))){
                        Text(quoteState.quote?.text ?: "none",
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            fontSize = 50.sp,
                            textAlign = TextAlign.Center)
                        val author = quoteState.quote?.author?.name ?: "Unknown"
                        Text("-$author",
                            fontSize = 35.sp,
                            textAlign = TextAlign.Center)
                    }
                }
                items(entries.size) {
                    val date = entries[it].date
                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ){
                        DateTextField(date)
                        Text("${entries[it].summary}",
                            fontSize = 25.sp,
                            modifier = Modifier.padding(20.dp))

                    }
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun formatDate(date: Date): String {
    val df = SimpleDateFormat("MM-dd-yyyy")
    return df.format(date)
}

@Composable
fun DateTextField(date: Long){
    val dateDate = Date(date)
    Text(formatDate(dateDate),
        fontWeight = FontWeight.Bold,
        fontSize = 35.sp,
        modifier = Modifier.padding(10.dp))
}