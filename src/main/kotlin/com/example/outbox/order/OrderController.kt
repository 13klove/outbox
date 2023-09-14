package com.example.outbox.order

import com.example.outbox.order.dto.CreateOrderCommand
import com.example.outbox.order.dto.CreateOrderRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping
    fun create(
        @RequestBody createOrderRequest: CreateOrderRequest
    ) {
        orderService.save(
            CreateOrderCommand(
                itemName = createOrderRequest.itemName
            )
        )
    }

}