package com.pada.medmaster.domain.model.patient

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeFrequency

class Intake(
    val id: Long? = null,
    val medicamentId: Long?, // todo - call to Medicament Aggregate Root by Id or Reference here?
    val form: IntakeForm, // move to medicament
    val dosage: Int, // change do String, provided by Medicament
    val intakeFrequency: IntakeFrequency?,
    val intakeDates: MutableList<IntakeDate> = mutableListOf(),  // Initialize the list
    val intakeLimit: Int,
    val treatment: Treatment?
) {
    fun addIntakeDates(intakeDates: List<IntakeDate>) {
        this.intakeDates.addAll(intakeDates)
    }
}
