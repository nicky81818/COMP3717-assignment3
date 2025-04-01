package com.example.assignment.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EntryDao {
    @Query("SELECT * FROM entry_table")
    fun getAll(): List<Entry>

    @Insert
    fun add(entry: Entry)
}