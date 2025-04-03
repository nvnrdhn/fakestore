package com.nvnrdhn.fakestore.model

class PriceModel(
    var value: Double = .0,
    var currency: String = "$",
) {
    fun toDisplayString(): String {
        return "$currency$value"
    }
}