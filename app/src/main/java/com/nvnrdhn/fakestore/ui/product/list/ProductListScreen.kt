@file:OptIn(ExperimentalMaterial3Api::class)

package com.nvnrdhn.fakestore.ui.product.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nvnrdhn.fakestore.R
import com.nvnrdhn.fakestore.base.BaseScreen
import com.nvnrdhn.fakestore.base.BaseScreen_Preview
import com.nvnrdhn.fakestore.model.PriceModel
import com.nvnrdhn.fakestore.model.ProductModel
import com.nvnrdhn.fakestore.ui.product.list.item.ProductItemLayout

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
        },
        onConnectionRetry = {
            vm.fetchProductList()
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
    val lazyListState = rememberLazyStaggeredGridState()
    val profileSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(state.productList) { item ->
                ProductItemLayout(
                    item = item
                )
            }
        }

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
            .shadow(
                elevation = 4.dp
            ),
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        actions = {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp)
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
            .padding(4.dp)
            .size(48.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(100)
            )
            .clickable { onClicked() }
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(28.dp),
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
        ProductListContent(
            state = ProductListState().apply {
                productList.addAll(
                    listOf(
                        ProductModel(
                            id = 1,
                            title = "Test",
                            description = "Test test est test",
                            price = PriceModel(512.53)
                        ),
                        ProductModel(
                            id = 2,
                            title = "Test 2",
                            description = "Test test est test",
                            price = PriceModel(512.53)
                        ),
                        ProductModel(
                            id = 3,
                            title = "Test 3",
                            description = "Test test est test",
                            price = PriceModel(512.53)
                        )
                    )
                )
            }
        )
    }
}