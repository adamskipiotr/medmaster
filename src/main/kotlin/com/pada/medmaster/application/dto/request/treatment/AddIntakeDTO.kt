package com.pada.medmaster.application.dto.request.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeFrequency

data class AddIntakeDTO(
    val form: IntakeForm,
    val medicamentId: Long,
)
