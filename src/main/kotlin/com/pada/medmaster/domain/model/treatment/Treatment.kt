package com.pada.medmaster.domain.model.treatment

import java.time.LocalDateTime

class Treatment(
    val id: Long? = null,
    val disease: String,
    val description: String,
    val code: String,
    val medicalProcedures: List<MedicalProcedure>,
    val intakes: List<Intake>,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
)