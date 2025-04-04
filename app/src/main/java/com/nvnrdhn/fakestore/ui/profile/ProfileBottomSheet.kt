package com.nvnrdhn.fakestore.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nvnrdhn.fakestore.model.ProfileModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    profileData: ProfileModel,
    loading: Boolean = false,
    onDismissRequest: () -> Unit = {},
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(
                    state = rememberScrollState(),
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (loading) {
                CircularProgressIndicator()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.CenterHorizontally),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = profileData.username,
                        onValueChange = {},
                        label = { Text(text = "Username") },
                        readOnly = true
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = profileData.name.firstName,
                        onValueChange = {},
                        label = { Text(text = "First name") },
                        readOnly = true
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = profileData.name.lastName,
                        onValueChange = {},
                        label = { Text(text = "Last name") },
                        readOnly = true
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = profileData.email,
                        onValueChange = {},
                        label = { Text(text = "Email") },
                        readOnly = true
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = profileData.phone,
                        onValueChange = {},
                        label = { Text(text = "Phone") },
                        readOnly = true
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = profileData.address.city,
                        onValueChange = {},
                        label = { Text(text = "City") },
                        readOnly = true
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = profileData.address.street,
                        onValueChange = {},
                        label = { Text(text = "Street") },
                        readOnly = true
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = profileData.address.zipcode,
                        onValueChange = {},
                        label = { Text(text = "Zipcode") },
                        readOnly = true
                    )
                }
            }
        }

    }
}