package com.nvnrdhn.fakestore.base

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
import kotlinx.coroutines.launch

abstract class BaseVM : ViewModel() {
    var loading by mutableStateOf(false)
    var error by mutableStateOf<Throwable?>(null)

    protected fun handleError(error: Throwable) {
        this.loading = false
        this.error = error
    }

    protected fun launchSafely(coroutine: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                coroutine()
            } catch (error: Throwable) {
                handleError(error)
            }
        }
    }

    protected fun <T> Flow<T>.launchSafelyIn(scope: CoroutineScope): Job = this
        .catch { handleError(it) }
        .launchIn(scope)
}