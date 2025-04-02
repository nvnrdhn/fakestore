package com.nvnrdhn.fakestore.di

import android.content.Context
import com.nvnrdhn.fakestore.helper.NavigationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    @Singleton
    fun provideNavigationHelper(@ApplicationContext context: Context): NavigationHelper {
        return NavigationHelper(context)
    }
}