package com.example.outbox.order.dto

data class CreateOrderCommand(
    val itemName: String
)

data class CreateOrderRequest(
    val itemName: String
)