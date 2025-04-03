package com.nvnrdhn.fakestore.repo.cart

import android.database.sqlite.SQLiteConstraintException
import com.nvnrdhn.fakestore.dao.CartItemDao
import com.nvnrdhn.fakestore.model.CartItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepoImpl @Inject constructor(
    private val cartItemDao: CartItemDao
): CartRepo {
    override fun fetchCartItems(): Flow<List<CartItemModel>> =
        cartItemDao.loadCartItems()

    override fun getCartItem(productId: Int): Flow<CartItemModel> = flow {
        emit(cartItemDao.getCartItemByProductId(productId))
    }

    override fun updateCart(cartItem: CartItemModel): Flow<Boolean> = flow {
        cartItemDao.updateCart(cartItem)
        emit(true)
    }.catch {
        emit(false)
    }

    override fun deleteCart(cartItem: CartItemModel): Flow<Boolean> = flow {
        cartItemDao.deleteCart(cartItem)
        emit(true)
    }.catch {
        emit(false)
    }

    override fun insertCart(cartItem: CartItemModel): Flow<Boolean> = flow {
        cartItemDao.insertCart(cartItem)
        emit(true)
    }.catch { e ->
        if (e is SQLiteConstraintException) {
            val mItem = cartItemDao.getCartItemByProductId(cartItem.productId)
            cartItemDao.updateCart(mItem.apply { productQuantity++ })
            emit(true)
        } else throw e
    }
}