package com.nvnrdhn.fakestore.base

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

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

    protected fun launchSafely(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        coroutine: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(coroutineContext) {
            withContext(Dispatchers.Main) {
                error = null
            }

            try {
                coroutine()
            } catch (error: Throwable) {
                withContext(Dispatchers.Main) {
                    handleError(error)
                }
            }
        }
    }

    protected fun <T> Flow<T>.launchSafelyIn(scope: CoroutineScope): Job = this
        .onStart { error = null }
        .catch { handleError(it) }
        .launchIn(scope)
}