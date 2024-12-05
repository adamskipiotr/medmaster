package com.pada.medmaster.application.dto.request.treatment

import java.time.LocalDateTime

data class TreatmentRequestDTO(
    val disease: String,
    val description: String,
    val code: TreatmentCodeDTO,
    val medicalProcedures: List<MedicalProcedureRequestDTO>,
    val intakes: List<IntakeRequestDTO>,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime,
    val patientId: Long,
    val maximalActiveTreatments: Int?
)