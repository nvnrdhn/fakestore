package com.nvnrdhn.fakestore.repo.auth

import com.nvnrdhn.fakestore.datamodel.LoginDataModel
import com.nvnrdhn.fakestore.datamodel.LoginRequestDataModel
import kotlinx.coroutines.flow.Flow

interface AuthRepo {
    fun login(request: LoginRequestDataModel): Flow<LoginDataModel>
}