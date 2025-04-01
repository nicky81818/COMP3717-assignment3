package com.example.assignment.data

class EntryRepository(private val entryDao: EntryDao) {
    fun insertEntity(entry: Entry){
        entryDao.add(entry)
    }

    fun getAll(): List<Entry>{
        return entryDao.getAll()
    }
}