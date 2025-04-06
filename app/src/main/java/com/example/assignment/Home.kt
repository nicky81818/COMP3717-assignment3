package com.example.assignment

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
                IconButton(onClick = {searchVisible = true}) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }

            LazyColumn {
                item {
                    Text(quoteState.quote?.text ?: "none")
                    Text(quoteState.quote?.author?.name ?: "Unknown")
                }
                items(entries.size) {
                    val date = entries[it].date
                    DateTextField(date)
                    Text("${entries[it].summary}")
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
    Text(formatDate(dateDate))
}