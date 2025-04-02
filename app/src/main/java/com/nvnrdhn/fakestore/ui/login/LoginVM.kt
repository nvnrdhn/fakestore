package com.nvnrdhn.fakestore.ui.login

import androidx.lifecycle.ViewModel
import com.nvnrdhn.fakestore.helper.NavigationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val navigationHelper: NavigationHelper
) : ViewModel() {
    val state = LoginState()

    fun login(onLoggedIn: (Boolean) -> Unit) {
        // todo: handle login logic

        navigationHelper.navigateToProductList()
        onLoggedIn(true)
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