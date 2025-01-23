package com.pada.medmaster.domain.model.patient

import java.time.LocalDateTime

class IntakeDate(
    val id: Long? = null,
    val date: LocalDateTime,
    val intake: Intake?
)