package com.example.noteapp.di

import android.app.Application
import com.example.noteapp.NoteApplication
import com.example.noteapp.activities.AddNoteActivity
import com.example.noteapp.activities.MainActivity
import com.example.noteapp.activities.UpdateNoteActivity
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    fun getNoteViewModel(): NoteViewModel
    fun inject(activity: MainActivity)
    fun inject(activity: AddNoteActivity)
    fun inject(activity: UpdateNoteActivity)
    fun getAuthComponentFactory(): AuthComponent.Factory


    // dùng builder trong trường hợp truyền rất nhiều thuộc tính hoặc thuộc tính bắt buộc hoặc có thuộc tính hoặc không đưa vào
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): AppComponent
//    }


    // sửa dụng khi ít tham số truyền vào
    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

}