package com.example.outbox.order.event

import com.example.outbox.order.OrderStatus
import com.example.outbox.event.DomainEvent

data class CreateOrder(
    val id: Long,
    val itemName: String,
    val status: OrderStatus
) : DomainEvent