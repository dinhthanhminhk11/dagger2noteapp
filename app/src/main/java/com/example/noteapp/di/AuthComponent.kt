package com.example.noteapp.di

import com.example.noteapp.activities.LoginActivity
import com.example.noteapp.viewmodel.LoginViewModel
import dagger.Subcomponent

// lãng phí bộ nhớ khởi tạo xong phải hủy nó đi kill activity cho đỡ lãng phí bộ nhớ giả pháp là dùng subcomponent

@Auth
@Subcomponent
interface AuthComponent {
    fun getLoginViewModel(): LoginViewModel
    fun inject(activity: LoginActivity)


    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthComponent
    }
}