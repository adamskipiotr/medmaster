package com.pada.medmaster.domain.treatment

import java.time.LocalDateTime

class Treatment(
    val disease: String,
    val description: String,
    val medicalProcedures: List<MedicalProcedure>,
    val intakes: List<Intake>,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
) {


}