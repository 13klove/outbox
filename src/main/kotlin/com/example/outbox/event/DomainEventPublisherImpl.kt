package com.example.outbox.event

import com.example.outbox.outbox.Outbox
import org.springframework.stereotype.Component

@Component
class DomainEventPublisherImpl : DomainEventPublisher {

    override fun publish(outbox: Outbox) {
        println("log: ${outbox.id}")
    }

}