package com.example.assignment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.assignment.data.Quote
import com.example.assignment.data.QuoteRepository

class QuoteState(private val repository: QuoteRepository) {
    var quote by mutableStateOf<Quote?>(null)

    suspend fun getQuote() {quote = repository.getQuote() }

}