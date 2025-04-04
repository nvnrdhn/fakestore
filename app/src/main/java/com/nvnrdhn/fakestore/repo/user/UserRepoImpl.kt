package com.nvnrdhn.fakestore.repo.user

import com.nvnrdhn.fakestore.datamodel.UserDataModel
import com.nvnrdhn.fakestore.service.UserApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val userApiService: UserApiService
): UserRepo {
    override fun getUsers(): Flow<List<UserDataModel>> = flow {
        emit(userApiService.getUsers())
    }
}