package com.pada.medmaster.application.dto.request.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeFrequency

data class IntakeRequestDTO(
    val medicamentId: Long,
    val form: IntakeForm,  // No need for @ManyToOne, this is an enum
    val dosage: Int,
    val intakeFrequency: IntakeFrequency,
    val intakeDates: List<IntakeDateRequestDTO>,
    val intakeLimit: Int
)
