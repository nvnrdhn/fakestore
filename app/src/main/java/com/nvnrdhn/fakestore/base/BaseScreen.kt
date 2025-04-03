package com.nvnrdhn.fakestore.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nvnrdhn.fakestore.R
import com.nvnrdhn.fakestore.ui.theme.FakeStoreTheme
import java.net.UnknownHostException


@Preview
@Composable
fun BaseScreen_Preview(
    vm: BaseVM = object : BaseVM() {},
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit = { Text(text = "Hello, World!") }
) {
    BaseScreen(
        vm = vm,
        topBar = topBar,
        floatingActionButton = floatingActionButton
    ) { content() }
}

@Composable
fun BaseScreen(
    vm: BaseVM,
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    onConnectionRetry: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    FakeStoreTheme {
        Scaffold(
            topBar = topBar,
            floatingActionButton = floatingActionButton
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                when {
                    vm.loading -> CircularProgressIndicator()
                    vm.error != null -> {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = stringResource(
                                    id = R.string.base_error_message,
                                    vm.error?.message?: ""
                                )
                            )

                            if (vm.error is UnknownHostException) {
                                Button(
                                    onClick = onConnectionRetry
                                ) {
                                    Text(
                                        text = stringResource(R.string.retry_button_text)
                                    )
                                }
                            }
                        }
                    }
                    else -> content(innerPadding)
                }
            }
        }
    }
}