package com.pada.medmaster.application.dto.request.patient

data class CreateIntakeRequest(
    val medicamentId: Long,
    val form: IntakeForm,
    val dosage: Int,
    val intakeFrequency: IntakeFrequency,
    val intakeDates: List<IntakeDateRequest>,
    val intakeLimit: Int
)
