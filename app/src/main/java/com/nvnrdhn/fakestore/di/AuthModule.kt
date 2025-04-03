package com.nvnrdhn.fakestore.di

import com.nvnrdhn.fakestore.repo.auth.AuthRepo
import com.nvnrdhn.fakestore.repo.auth.AuthRepoImpl
import com.nvnrdhn.fakestore.service.AuthApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthModule {
    companion object {
        @Provides
        fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
            return retrofit.create()
        }
    }

    @Binds
    abstract fun bindAuthRepo(authRepoImpl: AuthRepoImpl): AuthRepo
}