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
    val maximalActiveTreatments: Int?
) {
    init {
        require(code.isEmpty()) { "A treatment code must be provided" }
    }

    fun validateCreation(activeTreatmentsCount: Int) {
        if (activeTreatmentsCount == 0 && maximalActiveTreatments == null) {
            throw IllegalArgumentException("maximalActiveTreatments must be provided when no treatments with code '$code' exist.")
        }

        if (maximalActiveTreatments != null && activeTreatmentsCount >= maximalActiveTreatments) {
            throw IllegalStateException("Cannot add treatment. Adding this treatment would exceed the limit of $maximalActiveTreatments active treatments for code '$code'.")
        }
    }

    fun addMedicalProcedures(medicalProcedures: List<MedicalProcedure>) {
        this.medicalProcedures.addAll(medicalProcedures)
    }

    fun addIntakes(intakes: List<Intake>) {
        this.intakes.addAll(intakes)
    }
}
