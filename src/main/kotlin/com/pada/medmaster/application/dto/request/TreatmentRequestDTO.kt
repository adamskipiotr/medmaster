package com.pada.medmaster.application.dto.request

import java.time.LocalDateTime

data class TreatmentRequestDTO(
    val disease: String,
    val description: String,
    val code: String,
    val medicalProcedures: List<MedicalProcedureRequestDTO>,
    val intakes: List<IntakeRequestDTO>,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
)