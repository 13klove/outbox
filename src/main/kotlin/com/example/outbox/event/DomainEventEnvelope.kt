package com.example.outbox.event

class DomainEventEnvelope(
    val aggregateId: Long,
    val aggregateType: String,
    val type: String,
    val payload: DomainEvent
)