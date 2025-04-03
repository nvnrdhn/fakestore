package com.nvnrdhn.fakestore.ui.cart.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nvnrdhn.fakestore.base.BaseScreen_Preview

@Composable
fun CartDetailScreen(
    vm: CartDetailVM = viewModel()
) {

}

@Composable
fun CartDetailContent(
    state: CartDetailState
) {

}

@Preview
@Composable
fun CartDetailScreen_Preview() {
    BaseScreen_Preview(

    ) {
        CartDetailContent(
            state = CartDetailState()
        )
    }
}