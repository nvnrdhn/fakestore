package com.nvnrdhn.fakestore.ui.cart.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
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

@Composable
fun CartSummaryScreen(
    vm: CartSummaryVM = viewModel()
) {
    LaunchedEffect(vm) {
        vm.fetchCart()
    }

    BaseScreen(
        vm = vm,
        topBar = {
            BaseTopBar(
                title = stringResource(R.string.title_activity_cart_summary)
            )
        }
    ) {
        CartSummaryContent(
            state = vm.state
        )
    }
}

@Composable
fun CartSummaryContent(
    state: CartSummaryState = CartSummaryState()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(
                state = rememberScrollState()
            )
    ) {
        Column(
            modifier = Modifier.wrapContentHeight()
        ) {
            Text(
                text = stringResource(R.string.order_summary_title),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                state.cartItems.forEach { item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(40.dp)
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = item.productName
                            )

                            Text(
                                text = stringResource(
                                    id = R.string.item_order_price_text,
                                    item.productPrice.toString(),
                                    item.productQuantity
                                )
                            )
                        }

                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.total_price_text),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = state.totalPrice.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun CartSummaryScreen_Preview() {
    BaseScreen_Preview(
        topBar = {
            BaseTopBar(
                title = stringResource(R.string.title_activity_cart_summary)
            )
        }
    ) {
        CartSummaryContent(
            state = CartSummaryState().apply {
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