package com.pada.medmaster.domain.model.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeFrequency

class Intake(
    val id: Long? = null,
    var medicament: Medicament?,
    val form: IntakeForm,  // No need for @ManyToOne, this is an enum
    val dosage: Int,
    val intakeFrequency: IntakeFrequency?,
    var intakeDates: List<IntakeDate>? = mutableListOf(),  // Initialize the list
    val intakeLimit: Int,
    var treatment: Treatment?
)
