package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.domain.events.MissedIntakeEvent
import com.pada.medmaster.domain.service.patient.stubs.GetPatientsPortStub
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient.PublishIntakeMissedEventAdapter
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.spy
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.context.ApplicationEventPublisher
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class ScheduledIntakeValidationServiceTest {

    private val fixedClock = Clock.fixed(
        Instant.parse("2025-03-05T01:00:00Z"), // Fixed "now" in tests
        ZoneId.systemDefault()
    )


    private val getPatientsPort = GetPatientsPortStub()
    private val applicationEventPublisher: ApplicationEventPublisher = spy()
    private val publishIntakeMissedEventPort = PublishIntakeMissedEventAdapter(applicationEventPublisher)

    private var sut = ScheduledIntakeValidationService(getPatientsPort, publishIntakeMissedEventPort, fixedClock)

    @Test
    fun shouldNotCallApplicationEventPublisherWhenNoMissedIntakesDetected() {
        val pastClock = Clock.fixed(
            Instant.parse("2024-04-05T01:00:00Z"), // Fixed "now" in tests, only for purpose of testing unit functionality
            ZoneId.systemDefault()
        )
        sut = ScheduledIntakeValidationService(getPatientsPort, publishIntakeMissedEventPort, pastClock)

        sut.validateIntakes()

        verify(applicationEventPublisher, times(0)).publishEvent(any<MissedIntakeEvent>())
    }

    @Test
    fun shouldCallApplicationEventPublisherWhenSomeMissedIntakesDetected() {
        sut.validateIntakes()

        verify(applicationEventPublisher, times(5)).publishEvent(any<MissedIntakeEvent>())
    }

    @Test
    fun shouldCallApplicationEventPublisherWhenAllMissedIntakesDetected() {
        val futureClock = Clock.fixed(
            Instant.parse("2025-04-05T01:00:00Z"), // Fixed "now" in tests
            ZoneId.systemDefault()
        )
        sut = ScheduledIntakeValidationService(getPatientsPort, publishIntakeMissedEventPort, futureClock)

        sut.validateIntakes()

        verify(applicationEventPublisher, times(9)).publishEvent(any<MissedIntakeEvent>())
    }
}
