package com.nvnrdhn.fakestore.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nvnrdhn.fakestore.R
import com.nvnrdhn.fakestore.ui.base.BaseScreen
import com.nvnrdhn.fakestore.ui.base.BaseScreen_Preview

@Composable
fun LoginScreen(
    vm: LoginVM = viewModel(),
    finish: () -> Unit = {}
) {
    LaunchedEffect(vm) {
        if (vm.isLoggedIn()) finish()
    }

    BaseScreen(vm = vm) { innerPadding ->
        LoginScreenContent(
            state = vm.state,
            onUsernameChanged = { vm.onUsernameChanged(it) },
            onPasswordChanged = { vm.onPasswordChanged(it) },
            onLoginClicked = { vm.login { isLoggedIn -> if (isLoggedIn) finish() } },
            innerPadding = innerPadding
        )
    }
}

@Composable
private fun LoginScreenContent(
    state: LoginState = LoginState(),
    onUsernameChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onLoginClicked: () -> Unit = {},
    innerPadding: PaddingValues = PaddingValues()
) {
    val layoutDirection = LocalLayoutDirection.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = innerPadding.calculateTopPadding() + 24.dp,
                start = innerPadding.calculateStartPadding(layoutDirection) + 32.dp,
                end = innerPadding.calculateEndPadding(layoutDirection) + 32.dp,
                bottom = innerPadding.calculateBottomPadding()
            ),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.login_welcome_message),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        LoginTextField(
            value = state.username,
            onValueChange = { onUsernameChanged(it) },
            placeholder = stringResource(R.string.login_username_placeholder),
            leadingIcon = Icons.Default.Person,
            visualTransformation = VisualTransformation.None,
            supportingText = stringResource(R.string.login_username_hint)
        )

        LoginTextField(
            value = state.password,
            onValueChange = { onPasswordChanged(it) },
            placeholder = stringResource(R.string.login_password_placeholder),
            leadingIcon = Icons.Default.Lock,
            visualTransformation = PasswordVisualTransformation(),
            supportingText = stringResource(R.string.login_password_hint)
        )

        if (state.loginLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onLoginClicked() }
            ) {
                Text(
                    text = stringResource(R.string.login_button_text)
                )
            }
        }
    }
}

@Composable
private fun LoginTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String = "",
    leadingIcon: ImageVector,
    visualTransformation: VisualTransformation,
    supportingText: String = ""
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        visualTransformation = visualTransformation,
        maxLines = 1,
        supportingText = {
            if (supportingText.isNotEmpty()) {
                Text(
                    text = supportingText,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    )
}

@Preview
@Composable
fun LoginScreen_Preview() {
    BaseScreen_Preview { LoginScreenContent() }
}