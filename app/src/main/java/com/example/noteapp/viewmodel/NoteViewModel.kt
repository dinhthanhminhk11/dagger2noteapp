package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.database.repository.NoteRepository
import com.example.noteapp.model.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) {

    fun insertNote(note: Note) = GlobalScope.launch {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note) = GlobalScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = GlobalScope.launch {
        noteRepository.deleteNote(note)
    }

    fun getAllNote(): LiveData<List<Note>> = noteRepository.getAllNote()

//    class NoteViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
//
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return NoteViewModel(application) as T
//            }
//
//            throw IllegalArgumentException("Unable construct viewModel")
//        }
//
//    }

}