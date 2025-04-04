package com.nvnrdhn.fakestore.model

data class ProfileModel(
    val id: Int = 0,
    val name: NameModel = NameModel(),
    val email: String = "",
    val username: String = "",
    val phone: String = "",
    val address: AddressModel = AddressModel()
) {
    data class NameModel(
        val firstName: String = "",
        val lastName: String = ""
    )

    data class AddressModel(
        val city: String = "",
        val street: String = "",
        val number: String = "",
        val zipcode: String = ""
    )
}
