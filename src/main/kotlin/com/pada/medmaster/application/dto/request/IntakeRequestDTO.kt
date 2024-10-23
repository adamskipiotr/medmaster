package com.pada.medmaster.application.dto.request

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeFrequency

class IntakeRequestDTO(
    val medicament: MedicamentRequestDTO,
    val form: IntakeForm,  // No need for @ManyToOne, this is an enum
    val dosage: Int,
    val intakeFrequency: IntakeFrequency,
    val intakeDates: List<IntakeDateRequestDTO> = mutableListOf(),  // Initialize the list
    val intakeLimit: Int
)
