package com.oneeyedmen.cs

import java.math.BigDecimal

data class Order(
    val side: Side,
    val quantity: BigDecimal,
    val coinName: String,
    val price: BigDecimal,
    val userName: String
)