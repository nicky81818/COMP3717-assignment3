package com.example.assignment

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun Home(entriesState: EntriesState, quoteState: QuoteState) {
    Column (modifier = Modifier.safeDrawingPadding()){
        val entries = entriesState.entries
//        Button(onClick = {entriesState.refresh()}) {
//            Text("Refresh")
//        }
        Box {
            LazyColumn {
                item {
                    Text(quoteState.quote?.text ?: "none")
                    Text(quoteState.quote?.author?.get("name").toString() ?: "Unknown")
                }
                items(entries.size) {
                    val date = Date(entries[it].date)
                    Text(formatDate(date))
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