package com.pada.medmaster.domain.model.treatment


class TreatmentCode(
    val code: String,
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
}
