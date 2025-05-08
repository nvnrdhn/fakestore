package com.nvnrdhn.fakestore.usecase

import com.nvnrdhn.fakestore.datamodel.UserDataModel
import com.nvnrdhn.fakestore.model.ProfileModel
import javax.inject.Inject

class ProfileUseCase @Inject constructor() {
    fun getProfileData(response: List<UserDataModel>): ProfileModel {
        response.find { it.username == "donero" }
            ?.let {
                return ProfileModel(
                    id = it.id,
                    username = it.username,
                    name = ProfileModel.NameModel(
                        firstName = it.name.firstname,
                        lastName = it.name.lastname
                    ),
                    email = it.email,
                    phone = it.phone,
                    address = ProfileModel.AddressModel(
                        city = it.address.city,
                        street = it.address.street,
                        number = it.address.number,
                        zipcode = it.address.zipcode
                    )
                )
            }

        return ProfileModel()
    }
}