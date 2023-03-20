package com.example.notesapplofcoding.repositories

import com.example.notesapplofcoding.data.Note
import com.example.notesapplofcoding.data.db.NotesDatabase


class NotesRepo(
    notesDatabase: NotesDatabase
) {

    val notesDao = notesDatabase.noteDao

    suspend fun upsertNote(note: Note) = notesDao.upsertNote(note)

    suspend fun deleteNote(note: Note) = notesDao.deleteNote(note)

    fun getNotes() = notesDao.selectNotes()

    fun searchNotes(searchQuery: String) = notesDao.searchInNotesTitle(searchQuery)

    suspend fun deleteAllNotes() = notesDao.deleteAllNotes()

}