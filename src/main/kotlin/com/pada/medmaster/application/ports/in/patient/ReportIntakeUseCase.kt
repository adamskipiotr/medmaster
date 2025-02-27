package com.pada.medmaster.application.ports.`in`.patient

import com.pada.medmaster.application.dto.request.treatment.ReportIntakeRequest

fun interface ReportIntakeUseCase {
    fun report(patientId: Long, treatmentId: Long, intakeId: Long, reportIntakeRequest: ReportIntakeRequest)
}