package com.nvnrdhn.fakestore.repo.user

import com.nvnrdhn.fakestore.datamodel.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserRepo {
    fun getUsers(): Flow<List<UserDataModel>>
}