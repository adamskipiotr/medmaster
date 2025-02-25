package com.pada.medmaster.domain.model.patient

import java.time.LocalDateTime

class MedicalProcedure(
    val id: Long? = null,
    val name: String,
    val description: String,
    val procedureDate: LocalDateTime,
    val minimalRecoveryDate: LocalDateTime,
    val treatment: Treatment?
) {
    init {
        require(procedureDate.isAfter(LocalDateTime.now())) { "Medical Procedure must be scheduled for a future" } // refactor now()
        require(minimalRecoveryDate.isAfter(procedureDate)) { "Minimal recovery date must be after procedure date" }
    }

}
