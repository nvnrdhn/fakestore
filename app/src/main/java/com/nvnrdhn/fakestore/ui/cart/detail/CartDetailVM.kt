package com.nvnrdhn.fakestore.ui.cart.detail

import androidx.lifecycle.viewModelScope
import com.nvnrdhn.fakestore.model.CartItemModel
import com.nvnrdhn.fakestore.repo.cart.CartRepo
import com.nvnrdhn.fakestore.ui.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartDetailVM @Inject constructor(
    private val cartRepo: CartRepo
) : BaseVM() {
    val state = CartDetailState()

    fun fetchCart() {
        cartRepo.fetchCartItems()
            .onEach {
                state.cartItems.clear()
                state.cartItems.addAll(it)
            }
            .launchSafelyIn(viewModelScope)
    }

    fun onItemAdd(item: CartItemModel) {
        item.productQuantity++
        cartRepo.updateCart(item).launchSafelyIn(viewModelScope)
    }

    fun onItemRemove(item: CartItemModel) {
        if (item.productQuantity > 1) {
            item.productQuantity--
            cartRepo.updateCart(item).launchSafelyIn(viewModelScope)
        } else {
            state.cartItems.remove(item)
            cartRepo.deleteCart(item).launchSafelyIn(viewModelScope)
        }
    }

    fun checkout() {

    }
}