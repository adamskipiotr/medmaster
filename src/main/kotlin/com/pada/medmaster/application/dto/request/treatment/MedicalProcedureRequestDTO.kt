package com.pada.medmaster.application.dto.request.treatment

import java.time.LocalDateTime

class MedicalProcedureRequestDTO(
    val name: String,
    val description: String,
    val procedureDate: LocalDateTime,
    val minimalRecoveryDate: LocalDateTime,
)