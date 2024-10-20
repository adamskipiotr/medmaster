package com.pada.medmaster.domain.model.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeDateEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeFrequency
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.MedicamentEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.TreatmentEntity

class Intake(
    var id: Long = 0,
    val medicament: Medicament,
    val form: IntakeForm,  // No need for @ManyToOne, this is an enum
    val dosage: Int,
    val intakeFrequency: IntakeFrequency,
    val intakeDates: List<IntakeDate> = mutableListOf(),  // Initialize the list
    val intakeLimit: Int,
    val treatment: Treatment
)
