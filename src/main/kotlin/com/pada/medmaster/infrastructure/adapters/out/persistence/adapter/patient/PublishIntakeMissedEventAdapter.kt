package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.application.ports.out.patient.PublishIntakeMissedEventPort
import com.pada.medmaster.domain.events.MissedIntakeEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class PublishIntakeMissedEventAdapter(
    private val applicationEventPublisher: ApplicationEventPublisher
) : PublishIntakeMissedEventPort {

    override fun publish(missedIntakes: List<MissedIntakeEvent>) {
        missedIntakes.forEach { applicationEventPublisher.publishEvent(it) }
    }
}