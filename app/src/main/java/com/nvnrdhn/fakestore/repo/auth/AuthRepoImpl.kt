package com.nvnrdhn.fakestore.repo.auth

import com.nvnrdhn.fakestore.datamodel.LoginDataModel
import com.nvnrdhn.fakestore.datamodel.LoginRequestDataModel
import com.nvnrdhn.fakestore.service.AuthApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val apiService: AuthApiService
) : AuthRepo {
    override fun login(request: LoginRequestDataModel): Flow<LoginDataModel> = flow {
        emit(apiService.login(request))
    }
}