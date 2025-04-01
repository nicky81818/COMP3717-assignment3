package com.example.assignment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.Date

class AddEntryState {
    // date state
    var date:Long by mutableLongStateOf(currentTimeToLong())
    var readableDate = formatDate(Date(date))

    // summary state
    var summary by mutableStateOf("")
    val onSummaryChanged:(String) -> Unit = {
        summary = it
    }
    val summaryLabel = "Summary"

    private fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }
}