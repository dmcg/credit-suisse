package com.oneeyedmen.cs

import java.math.BigDecimal

fun List<Order>.orderBoardFor(coinName: String, side: Side, limit: Int = 10): List<OrderBoardLine> =
    this.summarisedBy(coinName, side).take(limit)

private fun List<Order>.summarisedBy(coinName: String, side: Side): List<OrderBoardLine> {
    val grouped = filter { it.side == side && it.coinName == coinName}
        .groupBy(Order::price)
    val merged = grouped.values.map { it.toOrderBoardLine() }
    return when (side) {
        Side.SELL -> merged.sortedBy(OrderBoardLine::price)
        Side.BUY -> merged.sortedByDescending(OrderBoardLine::price)
    }
}

private fun List<Order>.toOrderBoardLine(): OrderBoardLine {
    require(this.isNotEmpty())
    val total = this.map { it.quantity }.fold(BigDecimal.ZERO, BigDecimal::plus)
    return OrderBoardLine(total, this.first().coinName, this.first().price)
}