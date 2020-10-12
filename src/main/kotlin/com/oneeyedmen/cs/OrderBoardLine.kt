package com.oneeyedmen.cs

import java.math.BigDecimal

data class OrderBoardLine(
    val totalQuantity: BigDecimal,
    val coinName: String,
    val price: BigDecimal,
)