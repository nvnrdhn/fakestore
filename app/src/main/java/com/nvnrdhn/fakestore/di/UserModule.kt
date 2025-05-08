package com.nvnrdhn.fakestore.di

import com.nvnrdhn.fakestore.repo.user.UserRepo
import com.nvnrdhn.fakestore.repo.user.UserRepoImpl
import com.nvnrdhn.fakestore.service.UserApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserModule {
    companion object {
        @Provides
        fun provideUserApiService(retrofit: Retrofit): UserApiService {
            return retrofit.create()
        }
    }

    @Binds
    abstract fun bindUserRepo(userRepoImpl: UserRepoImpl): UserRepo
}