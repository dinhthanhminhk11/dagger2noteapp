package com.example.noteapp.di

import android.app.Application
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    subcomponents = [AuthComponent::class]
)
class AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(application: Application): NoteDatabase {
        return NoteDatabase.getInstance(application)
    }


    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.getNoteDao()
    }
}