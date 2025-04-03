package com.nvnrdhn.fakestore.ui.product.detail

import androidx.lifecycle.viewModelScope
import com.nvnrdhn.fakestore.R
import com.nvnrdhn.fakestore.helper.NavigationHelper
import com.nvnrdhn.fakestore.helper.ResourceHelper
import com.nvnrdhn.fakestore.repo.cart.CartRepo
import com.nvnrdhn.fakestore.repo.product.ProductRepo
import com.nvnrdhn.fakestore.ui.base.BaseVM
import com.nvnrdhn.fakestore.usecase.CartUseCase
import com.nvnrdhn.fakestore.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailVM @Inject constructor(
    private val navigationHelper: NavigationHelper,
    private val resourceHelper: ResourceHelper,
    private val productRepo: ProductRepo,
    private val cartRepo: CartRepo,
    private val productUseCase: ProductUseCase,
    private val cartUseCase: CartUseCase
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

    fun addToCart() {
        val cartItem = cartUseCase.convertProductToCartItem(state.product)

        cartRepo.insertCart(cartItem)
            .flowOn(Dispatchers.IO)
            .onEach { success ->
                val message = if (success) R.string.product_add_success_message
                else R.string.product_add_error_unknown_message

                snackbarHostState.showSnackbar(
                    message = resourceHelper.getStringResource(message)
                )
            }
            .catch {
                snackbarHostState.showSnackbar(
                    message = resourceHelper.getStringResource(
                        R.string.base_error_message,
                        it.message ?: ""
                    )
                )
            }
            .launchSafelyIn(viewModelScope)
    }

    fun onCartClicked() {
        navigationHelper.navigateToCartDetail()
    }
}