package com.pada.medmaster.domain.model.treatment

import java.time.LocalDateTime

class Treatment(
    val id: Long? = null,
    val disease: String,
    val description: String,
    val code: String,
    val medicalProcedures: MutableList<MedicalProcedure> = mutableListOf(),
    val intakes: MutableList<Intake> = mutableListOf(),
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime,
) {
    fun addMedicalProcedures(medicalProcedures: List<MedicalProcedure>) {
        this.medicalProcedures.addAll(medicalProcedures)
    }

    fun addIntakes(intakes: List<Intake>) {
        this.intakes.addAll(intakes)
    }
}
