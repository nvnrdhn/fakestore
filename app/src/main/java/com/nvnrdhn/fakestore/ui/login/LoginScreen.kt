package com.nvnrdhn.fakestore.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nvnrdhn.fakestore.R

@Composable
fun LoginScreen(
    vm: LoginVM = viewModel(),
    onLoggedIn: () -> Unit = {}
) {
    LaunchedEffect(vm) {
        if (vm.isLoggedIn()) onLoggedIn()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 24.dp,
                vertical = 32.dp
            ),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        LoginTextField(
            value = vm.state.username,
            onValueChange = { vm.onUsernameChanged(it) },
            placeholder = stringResource(R.string.login_username_placeholder),
            leadingIcon = Icons.Default.Person,
            visualTransformation = VisualTransformation.None
        )

        LoginTextField(
            value = vm.state.password,
            onValueChange = { vm.onPasswordChanged(it) },
            placeholder = stringResource(R.string.login_password_placeholder),
            leadingIcon = Icons.Default.Lock,
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { vm.login(onLoggedIn) }
        ) {
            Text(
                text = stringResource(R.string.login_button_text)
            )
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
    visualTransformation: VisualTransformation
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
                tint = Color.Black,
                contentDescription = null
            )
        },
        visualTransformation = visualTransformation,
        maxLines = 1
    )
}

@Preview
@Composable
fun LoginLayout_Preview() {
    LoginScreen()
}