package com.pada.medmaster.domain.events

import org.springframework.context.ApplicationEvent // todo extend ApplicationEvent?
import java.time.LocalDateTime

data class MissedIntakeEvent (
    val patientId: Long,
    val treatmentId: Long,
    val intakeId: Long,
    val missedIntakeTimeLimit: LocalDateTime
)