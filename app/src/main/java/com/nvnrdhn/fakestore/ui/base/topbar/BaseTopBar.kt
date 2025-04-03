package com.nvnrdhn.fakestore.ui.base.topbar

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(
    title: String = "",
    actions: @Composable RowScope.() -> Unit = {},
    showBackArrow: Boolean = true
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp
            ),
        title = { Text(text = title) },
        actions = actions,
        navigationIcon = {
            if (showBackArrow) {
                IconButton(
                    onClick = { onBackPressedDispatcher?.onBackPressed() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}