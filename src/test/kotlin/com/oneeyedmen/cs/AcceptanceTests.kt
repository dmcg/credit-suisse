package com.oneeyedmen.cs

import com.oneeyedmen.cs.Side.BUY
import com.oneeyedmen.cs.Side.SELL
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

typealias Quantity = BigDecimal
typealias Price = BigDecimal

class AcceptanceTests {

    @Test
    fun `it meets the spec`() {
        val orders = listOf(
            Order(SELL, Quantity("350.1"), "Ethereum", Price("13.6"), "user1"),
            Order(SELL, Quantity("50.5"), "Ethereum", Price("14"), "user2"),
            Order(SELL, Quantity("441.8"), "Ethereum", Price("13.9"), "user3"),
            Order(SELL, Quantity("3.5"), "Ethereum", Price("13.6"), "user4")
        )
        assertEquals(
            listOf(
                OrderBoardLine(Quantity("353.6"), "Ethereum", Price("13.6")),
                OrderBoardLine(Quantity("441.8"), "Ethereum", Price("13.9")),
                OrderBoardLine(Quantity("50.5"), "Ethereum", Price("14"))
            ),
            orders.orderBoardFor("Ethereum", SELL)
        )
    }

    @Test
    fun `it shows only particular coin`() {
        val orders = listOf(
            Order(SELL, Quantity("350.1"), "Ethereum", Price("13.6"), "user1"),
            Order(SELL, Quantity("50.5"), "Ethereum", Price("14"), "user2"),
            Order(SELL, Quantity("441.8"), "Ethereum", Price("13.9"), "user3"),
            Order(SELL, Quantity("3.5"), "Litecoin", Price("13.6"), "user4")
        )
        assertEquals(
            listOf(
                OrderBoardLine(Quantity("350.1"), "Ethereum", Price("13.6")),
                OrderBoardLine(Quantity("441.8"), "Ethereum", Price("13.9")),
                OrderBoardLine(Quantity("50.5"), "Ethereum", Price("14"))
            ),
            orders.orderBoardFor("Ethereum", SELL)
        )
    }

    @Test
    fun `it shows only a particular side`() {
        val orders = listOf(
            Order(SELL, Quantity("350.1"), "Ethereum", Price("13.6"), "user1"),
            Order(SELL, Quantity("50.5"), "Ethereum", Price("14"), "user2"),
            Order(SELL, Quantity("441.8"), "Ethereum", Price("13.9"), "user3"),
            Order(BUY, Quantity("3.5"), "Ethereum", Price("13.6"), "user4")
        )
        assertEquals(
            listOf(
                OrderBoardLine(Quantity("350.1"), "Ethereum", Price("13.6")),
                OrderBoardLine(Quantity("441.8"), "Ethereum", Price("13.9")),
                OrderBoardLine(Quantity("50.5"), "Ethereum", Price("14"))
            ),
            orders.orderBoardFor("Ethereum", SELL)
        )
    }

    @Test
    fun `it sorts BUY orders descending`() {
        val orders = listOf(
            Order(BUY, Quantity("350.1"), "Ethereum", Price("13.6"), "user1"),
            Order(BUY, Quantity("50.5"), "Ethereum", Price("14"), "user2"),
            Order(BUY, Quantity("441.8"), "Ethereum", Price("13.9"), "user3"),
            Order(BUY, Quantity("3.5"), "Ethereum", Price("13.6"), "user4")
        )
        assertEquals(
            listOf(
                OrderBoardLine(Quantity("50.5"), "Ethereum", Price("14")),
                OrderBoardLine(Quantity("441.8"), "Ethereum", Price("13.9")),
                OrderBoardLine(Quantity("353.6"), "Ethereum", Price("13.6"))
            ),
            orders.orderBoardFor("Ethereum", BUY)
        )
    }

    @Test
    fun `it runs quite fast with lots of orders`() {
        val orders = (1..1_000_000).map {
            Order(SELL, Quantity("1"), "Ethereum", Price.valueOf(it.toLong()), "user1")
        }
        assertEquals(
            listOf(
                OrderBoardLine(Price("1"), "Ethereum", Price("1")),
                OrderBoardLine(Price("1"), "Ethereum", Price("2")),
                OrderBoardLine(Price("1"), "Ethereum", Price("3")),
                OrderBoardLine(Price("1"), "Ethereum", Price("4")),
            ),
            orders.orderBoardFor("Ethereum", SELL, limit = 4)
        )
    }
}