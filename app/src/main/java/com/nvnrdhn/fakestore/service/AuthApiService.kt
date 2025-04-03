package com.nvnrdhn.fakestore.service

import com.nvnrdhn.fakestore.datamodel.LoginDataModel
import com.nvnrdhn.fakestore.datamodel.LoginRequestDataModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDataModel): LoginDataModel
}