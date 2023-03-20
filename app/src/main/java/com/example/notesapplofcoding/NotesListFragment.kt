package com.example.notesapplofcoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapplofcoding.databinding.FragmentNotesListBinding
import com.example.notesapplofcoding.viewmodel.NotesViewModel
import kotlinx.coroutines.flow.collect


class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
    private lateinit var binding: FragmentNotesListBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesListBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerview()

        lifecycleScope.launchWhenStarted {
            viewModel.notes.collect { notesList ->
                notesAdapter.differ.submitList(notesList)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.searchNotes.collect { searchedNotes ->
                notesAdapter.differ.submitList(searchedNotes)
            }
        }

        binding.edSearch.addTextChangedListener {
            viewModel.searchNotes(it.toString().trim())
        }

        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
        }

        notesAdapter.onClick = { note ->
            val bundle = Bundle().apply {
                putParcelable("note",note)
            }

            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment,bundle)
        }

    }

    private fun setupRecyclerview() {
        notesAdapter = NotesAdapter()
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
            addItemDecoration(VerticaleItemDecoration(40))
        }
    }


}