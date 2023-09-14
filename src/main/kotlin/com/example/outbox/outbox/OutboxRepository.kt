package com.example.outbox.outbox

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface OutboxRepository : JpaRepository<Outbox, Long> {

    fun findAllByOrderByCreatedAt(pageable: Pageable): List<Outbox>

}