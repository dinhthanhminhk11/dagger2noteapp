package com.example.noteapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.NoteApplication
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityAddNoteBinding
import com.example.noteapp.di.DaggerAppComponent
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_add_note.*
import javax.inject.Inject

class AddNoteActivity : AppCompatActivity() {
    @Inject
    lateinit var noteViewModel: NoteViewModel

    //    private val noteViewModel: NoteViewModel by lazy {
//        ViewModelProvider(
//            this,
//            NoteViewModel.NoteViewModelFactory(this.application)
//        )[NoteViewModel::class.java]
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val appComponent = DaggerAppComponent.builder().application(application).build();
//        appComponent.inject(this)

        val appComponent = (application as NoteApplication).appComponent
        appComponent.inject(this)

        val binding: ActivityAddNoteBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_note)

        binding.btnAdd.setOnClickListener {
            val note =
                Note(binding.edtNoteTitle.text.toString(), binding.edtNoteDes.text.toString())
            noteViewModel.insertNote(note)
            finish()
        }

    }
}