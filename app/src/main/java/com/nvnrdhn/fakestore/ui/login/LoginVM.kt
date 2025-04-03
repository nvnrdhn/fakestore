package com.nvnrdhn.fakestore.ui.login

import androidx.lifecycle.viewModelScope
import com.nvnrdhn.fakestore.datamodel.LoginRequestDataModel
import com.nvnrdhn.fakestore.helper.NavigationHelper
import com.nvnrdhn.fakestore.repo.auth.AuthRepo
import com.nvnrdhn.fakestore.ui.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val navigationHelper: NavigationHelper,
    private val authRepo: AuthRepo
) : BaseVM() {
    val state = LoginState()

    fun login(onLoggedIn: (Boolean) -> Unit) {
        state.loginLoading = true

        val loginRequest = LoginRequestDataModel(
            username = state.username,
            password = state.password
        )

        authRepo.login(loginRequest)
            .flowOn(Dispatchers.IO)
            .onEach {
                if (it.token.isNotEmpty()) {
                    onLoggedIn(true)
                    navigationHelper.navigateToProductList()
                } else {
                    onLoggedIn(false)
                }
            }
            .onCompletion { state.loginLoading = false }
            .launchSafelyIn(viewModelScope)
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