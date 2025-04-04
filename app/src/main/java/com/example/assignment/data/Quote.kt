package com.example.assignment.data

import com.google.gson.JsonObject

data class Quote(val text: String, val author: Author)

data class Author(val name: String)