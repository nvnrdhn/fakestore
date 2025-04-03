package com.nvnrdhn.fakestore.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginState {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var loginLoading by mutableStateOf(false)
}