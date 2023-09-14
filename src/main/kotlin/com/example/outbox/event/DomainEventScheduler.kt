package com.example.outbox.event

import com.example.outbox.outbox.OutboxService
import mu.KLogging
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class DomainEventScheduler(
    private val outboxService: OutboxService,
    private val domainEventPublisher: DomainEventPublisher
) {

    companion object : KLogging() {
        const val PAGE_SIZE = 100
    }


    @SchedulerLock(
        name = "domainEventScheduler" ,
        lockAtLeastFor = "PT10S",
        lockAtMostFor = "PT10S",
    )
    @Scheduled(cron = "10 * * * * *")
    fun run() {
        val successIds = mutableListOf<Long>()
        outboxService.getAll(pageSize = PAGE_SIZE).forEach {
            try {
                domainEventPublisher.publish(outbox = it)
                successIds.add(it.id)
            } catch (e: Exception) {
                logger.error { "outbox fail id: $it" }
            }
        }
        outboxService.deleteByIds(ids = successIds)
    }

}