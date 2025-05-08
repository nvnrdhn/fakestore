package com.nvnrdhn.fakestore.datamodel

data class UserDataModel(
    val id: Int = 0,
    val name: NameDataModel = NameDataModel(),
    val email: String = "",
    val username: String = "",
    val phone: String = "",
    val address: AddressDataModel = AddressDataModel()
) {
    data class NameDataModel(
        val firstname: String = "",
        val lastname: String = ""
    )

    data class AddressDataModel(
        val city: String = "",
        val street: String = "",
        val number: String = "",
        val zipcode: String = ""
    )
}
