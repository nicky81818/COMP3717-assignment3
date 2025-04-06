package com.example.assignment

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.data.EntryRepository
import com.example.assignment.data.Entry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class EntriesState(private val repository: EntryRepository): ViewModel() {
    var entries = repository.getAll().toMutableStateList()
    var searchFlow = MutableStateFlow("")

    init {
        collectSearchInputs()
    }

    fun add(entry: Entry){
        repository.insertEntity(entry)
    }

    fun refresh(){
        entries.apply {
            clear()
            addAll(repository.getAll())
        }
    }

    private fun collectSearchInputs() {
        viewModelScope.launch {
            searchFlow
                .debounce(1000L)
                .collect{
                    entries = repository.search(it).toMutableStateList()
                }
        }
    }

    fun search(text: String){
        viewModelScope.launch {
            entries = repository.search(text).toMutableStateList()
        }
    }
}