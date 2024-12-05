package com.pada.medmaster.domain.model.treatment

import java.time.LocalDateTime

class Treatment(
    val id: Long? = null,
    val disease: String,
    val description: String,
    val code: TreatmentCode,
    val medicalProcedures: MutableList<MedicalProcedure> = mutableListOf(),
    val intakes: MutableList<Intake> = mutableListOf(),
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
) {
    fun validateCreation(activeTreatmentsCount: Int) {
        if (activeTreatmentsCount == 0 && code.maximalActiveTreatments == null) {
            throw IllegalArgumentException("maximalActiveTreatments must be provided when no treatments with code '$code' exist.")
        }

        if (code.maximalActiveTreatments != null && activeTreatmentsCount >= code.maximalActiveTreatments) {
            throw IllegalStateException("Cannot add treatment. Adding this treatment would exceed the limit of $code.maximalActiveTreatments active treatments for code '$code'.")
        }
    }

    fun addMedicalProcedures(medicalProcedures: List<MedicalProcedure>) {
        this.medicalProcedures.addAll(medicalProcedures)
    }

    fun addIntakes(intakes: List<Intake>) {
        this.intakes.addAll(intakes)
    }
}
