package com.pada.medmaster.domain.model.treatment

import java.time.LocalDateTime

class MedicalProcedure(
    val id: Long? = null,
    val name: String,
    val description: String,
    val procedureDate: LocalDateTime,
    val minimalRecoveryDate: LocalDateTime,
    val treatment: Treatment?
)
