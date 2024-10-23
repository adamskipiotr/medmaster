package com.pada.medmaster.application.dto.request

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.TreatmentEntity
import java.time.LocalDateTime
class MedicalProcedureRequestDTO(
    val name: String,
    val description: String,
    val procedureDate: LocalDateTime,
    val minimalRecoveryDate: LocalDateTime,
)