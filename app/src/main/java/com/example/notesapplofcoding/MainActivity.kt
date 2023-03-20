package com.example.notesapplofcoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notesapplofcoding.data.db.NotesDatabase
import com.example.notesapplofcoding.repositories.NotesRepo
import com.example.notesapplofcoding.viewmodel.NotesViewModel
import com.example.notesapplofcoding.viewmodel.providerFactory.NotesViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    val viewModel: NotesViewModel by lazy {
        val database = NotesDatabase.getDatabaseInstance(this)
        val notesRepo = NotesRepo(database)
        val notesProviderFactory = NotesViewModelProviderFactory(notesRepo)
        ViewModelProvider(this, notesProviderFactory)[NotesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}