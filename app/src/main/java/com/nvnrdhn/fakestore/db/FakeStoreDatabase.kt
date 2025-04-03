package com.nvnrdhn.fakestore.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nvnrdhn.fakestore.dao.CartItemDao
import com.nvnrdhn.fakestore.model.CartItemModel

@Database(version = 1, entities = [CartItemModel::class])
abstract class FakeStoreDatabase : RoomDatabase() {
    abstract fun cartDao(): CartItemDao
}