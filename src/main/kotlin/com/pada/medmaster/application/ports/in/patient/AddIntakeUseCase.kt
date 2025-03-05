package com.pada.medmaster.application.ports.`in`.patient

import com.pada.medmaster.application.dto.request.patient.CreateIntakeRequest

fun interface AddIntakeUseCase {
    fun addIntake(patientId: Long, treatmentId: Long, createIntakeRequest: CreateIntakeRequest)
}