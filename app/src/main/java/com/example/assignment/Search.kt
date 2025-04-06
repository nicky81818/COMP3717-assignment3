package com.example.assignment

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Search(){
    val entriesState: EntriesState = viewModel(LocalActivity.current as ComponentActivity)
    Log.i("nicole","after viewmodel")
    val entries = entriesState.entries
    Column {
        TextField(
            entriesState.searchFlow.collectAsState().value,
            onValueChange = {
                entriesState.searchFlow.value = it
                entriesState.search(entriesState.searchFlow.value)
            })

        LazyColumn {
            items(entries.size) {
                DateTextField(entries[it].date)
                Text(entries[it].summary ?: "N/A")
            }
        }
    }
}