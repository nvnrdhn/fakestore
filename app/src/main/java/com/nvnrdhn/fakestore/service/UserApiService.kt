package com.nvnrdhn.fakestore.service

import com.nvnrdhn.fakestore.datamodel.UserDataModel
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDataModel>
}