@file:OptIn(ExperimentalMaterial3Api::class)

package com.nvnrdhn.fakestore.ui.product.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nvnrdhn.fakestore.R
import com.nvnrdhn.fakestore.base.BaseScreen
import com.nvnrdhn.fakestore.base.BaseScreen_Preview

@Composable
fun ProductListScreen(
    vm: ProductListVM = viewModel()
) {
    LaunchedEffect(vm) {
        vm.fetchProductList()
    }

    BaseScreen(
        vm = vm,
        topBar = {
            ProductListTopBar(
                onProfileClicked = { vm.toggleProfileSheet() }
            )
        },
        floatingActionButton = {
            ProductCartButton(
                onClicked = { vm.onCartClicked() }
            )
        }
    ) { innerPadding ->
        ProductListContent(
            state = vm.state,
            onProfileSheetDismissed = { vm.toggleProfileSheet() },
            innerPadding = innerPadding
        )
    }
}

@Composable
private fun ProductListContent(
    state: ProductListState = ProductListState(),
    onProfileSheetDismissed: () -> Unit = {},
    innerPadding: PaddingValues = PaddingValues()
) {
    val profileSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Box(
        modifier = Modifier.padding(innerPadding)
    ) {
        if (state.isProfileSheetVisible) {
            ModalBottomSheet(
                modifier = Modifier.padding(
                    top = innerPadding.calculateTopPadding()
                ),
                onDismissRequest = onProfileSheetDismissed,
                sheetState = profileSheetState
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text("this is profile bottom sheet")
                }
            }
        }
    }
}

@Composable
private fun ProductListTopBar(
    onProfileClicked: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp
            ),
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onProfileClicked() },
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )
        }
    )
}

@Composable
private fun ProductCartButton(
    onClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(40.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(100)
            )
            .clickable { onClicked() }
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center),
            imageVector = Icons.Default.ShoppingCart,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(100)
                ),
            text = "50",
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}

@Preview
@Composable
fun ProductListScreen_Preview() {
    BaseScreen_Preview(
        topBar = {
            ProductListTopBar()
        },
        floatingActionButton = {
            ProductCartButton()
        }
    ) {
        ProductListContent()
    }
}