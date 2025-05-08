@file:OptIn(ExperimentalMaterial3Api::class)

package com.nvnrdhn.fakestore.ui.product.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nvnrdhn.fakestore.R
import com.nvnrdhn.fakestore.model.PriceModel
import com.nvnrdhn.fakestore.model.ProductModel
import com.nvnrdhn.fakestore.ui.base.BaseScreen
import com.nvnrdhn.fakestore.ui.base.BaseScreen_Preview
import com.nvnrdhn.fakestore.ui.base.topbar.BaseTopBar
import com.nvnrdhn.fakestore.ui.product.list.item.ProductItemLayout
import com.nvnrdhn.fakestore.ui.profile.ProfileBottomSheet

@Composable
fun ProductListScreen(
    vm: ProductListVM = viewModel()
) {
    LaunchedEffect(vm) {
        vm.fetchProfileData()
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
            innerPadding = innerPadding,
            onProductClicked = { vm.onProductClicked(it) },
            onSortByClicked = { vm.sortProduct(it) }
        )
    }
}

@Composable
private fun ProductListContent(
    state: ProductListState = ProductListState(),
    onProfileSheetDismissed: () -> Unit = {},
    innerPadding: PaddingValues = PaddingValues(),
    onProductClicked: (Int) -> Unit = {},
    onSortByClicked: (ProductListState.SortBy) -> Unit = {}
) {
    val lazyListState = rememberLazyStaggeredGridState()
    val profileSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.sort_by_text)
            )

            ProductListState.SortBy.entries.forEach { sortBy ->
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable { onSortByClicked(sortBy) }
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .background(
                            color = if (state.sortBy == sortBy) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.surface
                            },
                            shape = RoundedCornerShape(6.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 4.dp),
                        text = sortBy.name,
                        color = if (state.sortBy == sortBy) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                }
            }
        }
        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState,
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(
                items = state.displayList,
                span = {
                    if (it is ProductModel) {
                        StaggeredGridItemSpan.SingleLane
                    } else {
                        StaggeredGridItemSpan.FullLine
                    }
                }
            ) { item ->
                when (item) {
                    is ProductModel -> {
                        ProductItemLayout(
                            item = item,
                            onClicked = { onProductClicked(item.id) }
                        )
                    }

                    is String -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(6.dp)
                                )
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 6.dp),
                                text = item,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }
        }

        if (state.isProfileSheetVisible) {
            ProfileBottomSheet(
                modifier = Modifier.padding(
                    top = innerPadding.calculateTopPadding()
                ),
                onDismissRequest = onProfileSheetDismissed,
                sheetState = profileSheetState,
                profileData = state.profileData,
                loading = state.profileLoading
            )
        }
    }
}

@Composable
private fun ProductListTopBar(
    onProfileClicked: () -> Unit = {}
) {
    BaseTopBar(
        showBackArrow = false,
        title = stringResource(id = R.string.app_name),
        actions = {
            IconButton(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(32.dp)
                    .clickable { onProfileClicked() },
                onClick = onProfileClicked
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
private fun ProductListBottomBar() {
    NavigationBar {

    }
}

@Composable
private fun ProductCartButton(
    onClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier
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
    }
}

@Preview
@Composable
fun ProductListScreen_Preview() {
    BaseScreen_Preview(
        topBar = {
            ProductListTopBar()
        },
        bottomBar = {
            ProductListBottomBar()
        },
        floatingActionButton = {
            ProductCartButton()
        }
    ) {
        ProductListContent(
            state = ProductListState().apply {
                displayList.addAll(
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