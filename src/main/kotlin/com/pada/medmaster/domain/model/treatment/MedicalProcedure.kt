package com.pada.medmaster.domain.model.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.TreatmentEntity
import java.time.LocalDateTime
class MedicalProcedure(
    var id: Long = 0,
    val name: String,
    val description: String,
    val procedureDate: LocalDateTime,
    val minimalRecoveryDate: LocalDateTime,
    val  treatment: TreatmentEntity
)