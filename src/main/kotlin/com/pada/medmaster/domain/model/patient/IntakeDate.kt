package com.pada.medmaster.domain.model.patient

import java.time.LocalDateTime

class IntakeDate(
    val id: Long? = null,
    val date: LocalDateTime,
    val expectedDateMinGap: LocalDateTime?,
    val expectedDateMaxGap: LocalDateTime?,
    val intakeInTimeGap: Boolean,
    val overdose: Boolean,
    val intake: Intake?
)