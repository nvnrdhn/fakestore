package com.nvnrdhn.fakestore.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(

) : ViewModel() {
    val state = LoginState()

    fun login(onLoggedIn: () -> Unit) {
        // todo: handle login logic

        onLoggedIn()
    }

    fun isLoggedIn(): Boolean {

        return false
    }

    fun onUsernameChanged(username: String) {
        state.username = username
    }

    fun onPasswordChanged(password: String) {
        state.password = password
    }
}