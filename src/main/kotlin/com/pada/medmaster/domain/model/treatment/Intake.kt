package com.pada.medmaster.domain.model.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeFrequency

class Intake(
    val id: Long? = null,
    val medicamentId: Long?, // todo - call to Medicament Aggregate Root by Id or Reference here?
    val form: IntakeForm,  // No need for @ManyToOne, this is an enum
    val dosage: Int,
    val intakeFrequency: IntakeFrequency?,
    val intakeDates: MutableList<IntakeDate> = mutableListOf(),  // Initialize the list
    val intakeLimit: Int,
    val treatment: Treatment?
) {
    fun addIntakeDates(intakeDates: List<IntakeDate>) {
        this.intakeDates.addAll(intakeDates)
    }
}
