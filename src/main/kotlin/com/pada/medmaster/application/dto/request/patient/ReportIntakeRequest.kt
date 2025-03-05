package com.pada.medmaster.application.dto.request.patient

import java.time.LocalDateTime

data class ReportIntakeRequest(
        val medicamentId: Long,
        val form: IntakeForm,
        val dosage: Int,
        val date: LocalDateTime,
)
