package com.nvnrdhn.fakestore.ui.product.list

import androidx.lifecycle.ViewModel
import com.nvnrdhn.fakestore.base.BaseVM
import com.nvnrdhn.fakestore.helper.NavigationHelper
import com.nvnrdhn.fakestore.repo.product.ProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListVM @Inject constructor(
    private val navigationHelper: NavigationHelper,
    private val productRepo: ProductRepo
) : BaseVM() {
    val state = ProductListState()

    fun fetchProductList() {
        loading = true
//        productRepo.getProductList()
    }

    fun toggleProfileSheet() {
        state.isProfileSheetVisible = !state.isProfileSheetVisible
    }

    fun onCartClicked() {
        navigationHelper.navigateToCartDetail()
    }
}