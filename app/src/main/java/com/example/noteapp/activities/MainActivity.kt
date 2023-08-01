package com.example.noteapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.NoteApplication
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.di.DaggerAppComponent
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var noteViewModel: NoteViewModel

    //    private val noteViewModel: NoteViewModel by lazy {
//        ViewModelProvider(
//            this,
//            NoteViewModel.NoteViewModelFactory(this.application)
//        )[NoteViewModel::class.java]
//    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // chưa Đưa APPCOMPONENT vào Application xảy ra hiện tượng mỗi lần gọi sẽ sinh ra 1 repository mới lên không được kiểu này
//        val appComponent = DaggerAppComponent.builder().application(application).build();
//        appComponent.inject(this)

        val appComponent = (application as NoteApplication).appComponent
        appComponent.inject(this)

        initControls()
        initEvents()
    }

    private fun initEvents() {
        binding.btnOpenAddActivity.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControls() {
        val adapter = NoteAdapter(this@MainActivity, onItemClick, onItemDelete)

        binding.rvNote.setHasFixedSize(true)
        binding.rvNote.layoutManager = LinearLayoutManager(this)
        binding.rvNote.adapter = adapter

        this.noteViewModel.getAllNote().observe(this) {
            adapter.setNotes(it)
        }

    }

    private val onItemClick: (Note) -> Unit = {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE", it)
        startActivity(intent)

    }
    private val onItemDelete: (Note) -> Unit = {
        noteViewModel.deleteNote(it)
    }
}