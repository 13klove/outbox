package com.example.outbox.outbox

import com.example.outbox.event.DomainEventEnvelope
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Component
@Transactional
class OutboxService(
    private val outboxRepository: OutboxRepository,
    private val objectMapper: ObjectMapper,
) {

    fun save(domainEventEnvelopes: List<DomainEventEnvelope>) {
        outboxRepository.saveAll(
            domainEventEnvelopes.map {
                Outbox(
                    type = it.type,
                    aggregateId = it.aggregateId,
                    aggregateType = it.aggregateType,
                    payload = objectMapper.writeValueAsString(it.payload),
                    createdAt = Instant.now().toEpochMilli()
                )
            }
        )
    }

    fun getAll(pageSize: Int): List<Outbox> {
        return outboxRepository.findAllByOrderByCreatedAt(pageable = PageRequest.of(0, pageSize))
    }

    fun deleteByIds(ids: List<Long>) {
        outboxRepository.deleteAllByIdInBatch(ids)
    }
}