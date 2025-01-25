package com.pada.medmaster.application.dto.request.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeFrequency

data class CreateIntakeRequest(
    val medicamentId: Long,
    val form: IntakeForm,
    val dosage: Int,
    val intakeFrequency: IntakeFrequency,
    val intakeDates: List<IntakeDateRequest>,
    val intakeLimit: Int
)
