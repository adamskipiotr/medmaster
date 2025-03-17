package com.pada.medmaster.domain.events

import java.time.LocalDateTime

data class MissedIntakeEvent (
    val patientId: Long,
    val treatmentId: Long,
    val intakeId: Long,
    val missedIntakeTimeLimit: LocalDateTime
)