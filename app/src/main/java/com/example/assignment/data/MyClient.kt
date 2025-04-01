package com.example.assignment.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.gson.gson

// entry point for creating http requests
val client = HttpClient{
    // install the gson serializer into the ktor client
    install(ContentNegotiation){
        gson()
    }
}