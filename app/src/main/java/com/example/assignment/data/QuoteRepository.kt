package com.example.assignment.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class QuoteRepository (private val httpClient: HttpClient) {


    suspend fun getQuote(): Quote{
        val response = httpClient.get(RANDOM_QUOTE)
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Quote::class.java)
    }
}