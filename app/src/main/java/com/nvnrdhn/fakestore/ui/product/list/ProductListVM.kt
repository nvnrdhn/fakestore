package com.nvnrdhn.fakestore.ui.product.list

import androidx.lifecycle.viewModelScope
import com.nvnrdhn.fakestore.helper.NavigationHelper
import com.nvnrdhn.fakestore.repo.product.ProductRepo
import com.nvnrdhn.fakestore.ui.base.BaseVM
import com.nvnrdhn.fakestore.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductListVM @Inject constructor(
    private val navigationHelper: NavigationHelper,
    private val productRepo: ProductRepo,
    private val productUseCase: ProductUseCase
) : BaseVM() {
    val state = ProductListState()

    fun fetchProductList() {
        loading = true

        productRepo.getProductList()
            .map { productUseCase.mapProductResponse(it) }
            .flowOn(Dispatchers.IO)
            .onEach {
                state.productList.clear()
                state.productList.addAll(it)
            }
            .onCompletion {
                loading = false
            }
            .launchSafelyIn(viewModelScope)
    }

    fun toggleProfileSheet() {
        state.isProfileSheetVisible = !state.isProfileSheetVisible
    }

    fun onCartClicked() {
        navigationHelper.navigateToCartDetail()
    }

    fun onProductClicked(productId: Int) {
        navigationHelper.navigateToProductDetail(productId)
    }
}