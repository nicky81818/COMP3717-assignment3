package com.example.assignment.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EntryDao {
    @Query("SELECT * FROM entry_table")
    fun getAll(): List<Entry>

    @Query("SELECT * FROM entry_table WHERE summary LIKE :text")
    fun search(text: String): List<Entry>

    @Insert
    fun add(entry: Entry)
}