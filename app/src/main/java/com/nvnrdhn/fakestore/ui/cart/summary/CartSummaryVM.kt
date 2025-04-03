package com.nvnrdhn.fakestore.ui.cart.summary

import androidx.lifecycle.viewModelScope
import com.nvnrdhn.fakestore.repo.cart.CartRepo
import com.nvnrdhn.fakestore.ui.base.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartSummaryVM @Inject constructor(
    private val cartRepo: CartRepo
) : BaseVM() {
    val state = CartSummaryState()


    fun fetchCart() {
        cartRepo.fetchCartItems()
            .onEach {
                state.cartItems.clear()
                state.cartItems.addAll(it)
            }
            .launchSafelyIn(viewModelScope)
    }
}