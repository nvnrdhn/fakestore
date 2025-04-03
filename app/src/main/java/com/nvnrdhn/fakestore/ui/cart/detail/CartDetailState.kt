package com.nvnrdhn.fakestore.ui.cart.detail

import androidx.compose.runtime.mutableStateListOf
import com.nvnrdhn.fakestore.model.CartItemModel

class CartDetailState {
    val cartItems = mutableStateListOf<CartItemModel>()


}