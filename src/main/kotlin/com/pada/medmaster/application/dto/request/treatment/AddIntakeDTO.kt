package com.pada.medmaster.application.dto.request.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm

data class AddIntakeDTO(
    val form: IntakeForm,
    val medicamentId: Long,
)
