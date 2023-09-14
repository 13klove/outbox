package com.example.outbox.order

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "order_item")
class Order(
    val itemName: String,
    @Enumerated(value = EnumType.STRING)
    val status: OrderStatus,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    val createAt: Instant = Instant.now()
    var updateAt: Instant? = null

}

enum class OrderStatus {
    READY,
    PROCESS,
    COMPLETE
}