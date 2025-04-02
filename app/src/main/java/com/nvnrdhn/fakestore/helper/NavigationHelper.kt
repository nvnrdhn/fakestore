package com.nvnrdhn.fakestore.helper

import android.content.Context
import android.content.Intent
import com.nvnrdhn.fakestore.ui.cart.detail.CartDetailActivity
import com.nvnrdhn.fakestore.ui.product.list.ProductListActivity
import javax.inject.Inject

class NavigationHelper @Inject constructor(
    private val context: Context
) {
    fun navigateToProductList() {
        val intent = Intent(context, ProductListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun navigateToCartDetail() {
        val intent = Intent(context, CartDetailActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}