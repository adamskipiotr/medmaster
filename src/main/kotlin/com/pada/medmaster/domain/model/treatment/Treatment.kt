package com.pada.medmaster.domain.model.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeEntity
import java.time.LocalDateTime

class Treatment(
    var id: Long = 0,
    val disease: String,
    val description: String,
    val medicalProcedures: List<MedicalProcedure>,
    val intakes: List<IntakeEntity>,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
)