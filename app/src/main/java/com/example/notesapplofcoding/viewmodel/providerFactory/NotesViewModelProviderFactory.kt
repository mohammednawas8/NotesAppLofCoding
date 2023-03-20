package com.example.notesapplofcoding.viewmodel.providerFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapplofcoding.repositories.NotesRepo
import com.example.notesapplofcoding.viewmodel.NotesViewModel

class NotesViewModelProviderFactory(
    private val notesRepo: NotesRepo
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepo) as T
    }
}