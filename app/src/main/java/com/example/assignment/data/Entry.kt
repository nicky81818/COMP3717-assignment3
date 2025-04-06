package com.example.assignment.data

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "entry_table")
data class Entry (
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    val date: Long,
    val summary: String?,
    val quoteOfTheDay: String?,
    val author: String?
)