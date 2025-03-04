package com.pada.medmaster.application.dto.request.patient

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeFrequency
import java.time.LocalDateTime

data class ReportIntakeRequest(
        val medicamentId: Long,
        val form: IntakeForm,
        val dosage: Int,
        val date: LocalDateTime,
)
