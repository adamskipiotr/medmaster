package com.pada.medmaster.domain.model.patient

import java.time.LocalDateTime
import java.time.Clock

class MedicalProcedure(
    val id: Long? = null,
    val name: String,
    val description: String,
    val procedureDate: LocalDateTime,
    val minimalRecoveryDate: LocalDateTime,
    val treatment: Treatment?,
    clock: Clock = Clock.systemDefaultZone()
) {
    init {
        val now = LocalDateTime.now(clock)
        require(procedureDate.isAfter(now)) { "Medical Procedure must be scheduled for a future" } // refactor now()
        require(minimalRecoveryDate.isAfter(procedureDate)) { "Minimal recovery date must be after procedure date" }
    }
}
