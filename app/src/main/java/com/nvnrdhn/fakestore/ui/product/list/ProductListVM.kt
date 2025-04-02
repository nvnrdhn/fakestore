package com.nvnrdhn.fakestore.ui.product.list

import androidx.lifecycle.ViewModel
import com.nvnrdhn.fakestore.helper.NavigationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListVM @Inject constructor(
    private val navigationHelper: NavigationHelper
) : ViewModel() {
    val state = ProductListState()

    fun fetchProductList() {

    }

    fun toggleProfileSheet() {
        state.isProfileSheetVisible = !state.isProfileSheetVisible
    }

    fun onCartClicked() {
        navigationHelper.navigateToCartDetail()
    }
}