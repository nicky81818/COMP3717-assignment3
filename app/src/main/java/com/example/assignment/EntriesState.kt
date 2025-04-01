package com.example.assignment

import androidx.compose.runtime.toMutableStateList
import com.example.assignment.data.EntryRepository
import com.example.assignment.data.Entry

class EntriesState(private val repository: EntryRepository) {
    var entries = repository.getAll().toMutableStateList()

    fun add(entry: Entry){
        repository.insertEntity(entry)
    }

    fun refresh(){
        entries.apply {
            clear()
            addAll(repository.getAll())
        }
    }
}