package com.pada.medmaster.domain.model.treatment

import java.time.LocalDateTime

class Treatment(
    val id: Long? = null,
    val disease: String,
    val description: String,
    val code: String,
    var medicalProcedures: List<MedicalProcedure>,
    var intakes: List<Intake>,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
) {
    fun addMedicalProcedures(medicalProcedures: List<MedicalProcedure>) {
        this.medicalProcedures = medicalProcedures
        medicalProcedures.forEach{medicalProcedure -> medicalProcedure.treatment = this }
    }

    fun addIntakes(intakes: List<Intake>) {
        this.intakes =  intakes
        intakes.forEach { intake -> intake.treatment = this }
    }
}