package com.example.outbox.event

import com.example.outbox.outbox.Outbox

interface DomainEventPublisher {
    fun publish(outbox: Outbox)
}