package com.pada.medmaster.application.dto.request.patient

import java.time.LocalDateTime

class CreateMedicalProcedureRequest(
    val name: String,
    val description: String,
    val procedureDate: LocalDateTime,
    val minimalRecoveryDate: LocalDateTime,
)