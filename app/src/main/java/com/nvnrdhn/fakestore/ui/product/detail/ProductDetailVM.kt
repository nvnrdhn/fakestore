package com.nvnrdhn.fakestore.ui.product.detail

import androidx.lifecycle.viewModelScope
import com.nvnrdhn.fakestore.base.BaseVM
import com.nvnrdhn.fakestore.helper.NavigationHelper
import com.nvnrdhn.fakestore.repo.product.ProductRepo
import com.nvnrdhn.fakestore.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailVM @Inject constructor(
    private val navigationHelper: NavigationHelper,
    private val productRepo: ProductRepo,
    private val productUseCase: ProductUseCase
) : BaseVM() {
    val state = ProductDetailState()

    fun fetchProductDetail(productId: Int) {
        loading = true

        productRepo.getProductDetail(productId)
            .flowOn(Dispatchers.IO)
            .map { productUseCase.mapProductItem(it) }
            .onEach { state.product = it }
            .onCompletion { loading = false }
            .launchSafelyIn(viewModelScope)
    }

    fun onCartClicked() {
        navigationHelper.navigateToCartDetail()
    }
}