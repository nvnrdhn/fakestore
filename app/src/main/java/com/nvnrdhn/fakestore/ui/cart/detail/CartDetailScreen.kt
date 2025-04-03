package com.nvnrdhn.fakestore.ui.cart.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.nvnrdhn.fakestore.model.CartItemModel
import com.nvnrdhn.fakestore.ui.base.BaseScreen
import com.nvnrdhn.fakestore.ui.base.BaseScreen_Preview
import com.nvnrdhn.fakestore.ui.base.topbar.BaseTopBar
import com.nvnrdhn.fakestore.ui.cart.detail.item.CartDetailItemLayout

@Composable
fun CartDetailScreen(
    vm: CartDetailVM = viewModel()
) {
    LaunchedEffect(vm) {
        vm.fetchCart()
    }

    BaseScreen(
        vm = vm,
        topBar = {
            BaseTopBar(
                title = stringResource(R.string.title_activity_cart_detail)
            )
        }
    ) {
        CartDetailContent(
            state = vm.state,
            onItemAdd = { vm.onItemAdd(it) },
            onItemRemove = { vm.onItemRemove(it) },
            onCheckoutClicked = { vm.checkout() }
        )
    }
}

@Composable
fun CartDetailContent(
    state: CartDetailState = CartDetailState(),
    onItemAdd: (CartItemModel) -> Unit = {},
    onItemRemove: (CartItemModel) -> Unit = {},
    onCheckoutClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            Column(
                modifier = Modifier.wrapContentHeight()
            ) {
                Text(
                    text = "Shopping List",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Column(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (state.cartItems.isNotEmpty()) {
                        state.cartItems.forEach { item ->
                            CartDetailItemLayout(
                                item = item,
                                onItemAdd = onItemAdd,
                                onItemRemove = onItemRemove
                            )
                        }
                    } else {
                        Text(
                            text = stringResource(R.string.shopping_list_empty_message)
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                color = MaterialTheme.colorScheme.onBackground
            )

            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.cartItems.isNotEmpty(),
                    onClick = onCheckoutClicked
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null
                        )

                        Text(
                            text = stringResource(R.string.checkout_button_text),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CartDetailScreen_Preview() {
    BaseScreen_Preview(
        topBar = {
            BaseTopBar(
                title = stringResource(R.string.title_activity_cart_detail)
            )
        }
    ) {
        CartDetailContent(
            state = CartDetailState().apply {
                cartItems.addAll(
                    listOf(
                        CartItemModel(
                            productQuantity = 2,
                            productName = "Test s",
                            productPrice = 412.42
                        ),
                        CartItemModel(
                            productQuantity = 3,
                            productName = "Test s",
                            productPrice = 412.42
                        ),
                        CartItemModel(
                            productQuantity = 1,
                            productName = "Test s",
                            productPrice = 412.42
                        )
                    )
                )
            }
        )
    }
}