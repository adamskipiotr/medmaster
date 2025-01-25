package com.pada.medmaster.application.dto.request.treatment

import java.time.LocalDateTime

data class CreateTreatmentRequest(
    val disease: String,
    val description: String,
    val code: String,
    val medicalProcedures: List<CreateMedicalProcedureRequest>,
    val intakes: List<CreateIntakeRequest>,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime,
)