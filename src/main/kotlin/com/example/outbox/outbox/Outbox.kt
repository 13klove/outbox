package com.example.outbox.outbox

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Outbox(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val type: String,
    val aggregateId: Long,
    val aggregateType: String,
    val payload: String,
    val createdAt: Long
)
