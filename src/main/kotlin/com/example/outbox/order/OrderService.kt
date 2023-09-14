package com.example.outbox.order

import com.example.outbox.order.dto.CreateOrderCommand
import com.example.outbox.order.event.CreateOrder
import com.example.outbox.event.DomainEventEnvelope
import com.example.outbox.outbox.OutboxService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderService(
    private val orderRepository: OrderRepository,
    private val outboxService: OutboxService
) {

    fun save(command: CreateOrderCommand) {
        val order = orderRepository.save(
            Order(
                itemName = command.itemName,
                status = OrderStatus.READY
            )
        )
        outboxService.save(
            listOf(
                DomainEventEnvelope(
                    aggregateId = order.id,
                    aggregateType = order.javaClass.simpleName,
                    type = CreateOrder::class.java.simpleName,
                    payload = CreateOrder(
                        id = order.id,
                        itemName = order.itemName,
                        status = order.status
                    )
                )
            )
        )
    }

}