package com.nvnrdhn.fakestore.di

import com.nvnrdhn.fakestore.dao.CartItemDao
import com.nvnrdhn.fakestore.db.FakeStoreDatabase
import com.nvnrdhn.fakestore.repo.cart.CartRepo
import com.nvnrdhn.fakestore.repo.cart.CartRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CartModule {
    companion object {
        @Provides
        fun provideCartDao(db: FakeStoreDatabase): CartItemDao {
            return db.cartDao()
        }
    }

    @Binds
    abstract fun bindCartRepo(cartRepoImpl: CartRepoImpl): CartRepo
}