package com.nvnrdhn.fakestore.base

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException

abstract class BaseVM : ViewModel() {
    var loading by mutableStateOf(false)
    var error by mutableStateOf<Throwable?>(null)

    val snackbarHostState = SnackbarHostState()

    protected fun handleError(error: Throwable) {
        this.loading = false

        when (error) {
            is HttpException -> {
                viewModelScope.launch {
                    snackbarHostState.showSnackbar(
                        message = error.response()?.errorBody()?.string() ?: ""
                    )
                }
            }

            else -> this.error = error
        }
    }

    protected fun launchSafely(coroutine: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                error = null
                coroutine()
            } catch (error: Throwable) {
                handleError(error)
            }
        }
    }

    protected fun <T> Flow<T>.launchSafelyIn(scope: CoroutineScope): Job = this
        .onStart { error = null }
        .catch { handleError(it) }
        .launchIn(scope)
}