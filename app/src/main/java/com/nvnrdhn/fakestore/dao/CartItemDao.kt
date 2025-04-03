package com.nvnrdhn.fakestore.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nvnrdhn.fakestore.model.CartItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDao {
    @Query("SELECT * FROM cart")
    fun loadCartItems(): Flow<List<CartItemModel>>

    @Query("SELECT * FROM cart WHERE productId = :productId")
    suspend fun getCartItemByProductId(productId: Int): CartItemModel

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertCart(cartItem: CartItemModel)

    @Update
    suspend fun updateCart(cartItem: CartItemModel)

    @Delete
    suspend fun deleteCart(cartItem: CartItemModel)
}