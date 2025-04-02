package com.nvnrdhn.fakestore.di

import com.nvnrdhn.fakestore.repo.product.ProductRepo
import com.nvnrdhn.fakestore.repo.product.ProductRepoImpl
import com.nvnrdhn.fakestore.service.ProductApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProductModule {
    companion object {
        @Provides
        fun provideProductApiService(retrofit: Retrofit): ProductApiService {
            return retrofit.create()
        }
    }

    @Binds
    abstract fun bindProductRepo(productRepoImpl: ProductRepoImpl): ProductRepo
}