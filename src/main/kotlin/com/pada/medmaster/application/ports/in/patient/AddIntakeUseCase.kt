package com.pada.medmaster.application.ports.`in`.patient

import com.pada.medmaster.application.dto.request.treatment.CreateIntakeRequest

interface AddIntakeUseCase {
    fun addIntake(patientId: Long, treatmentId: Long, createIntakeRequest: CreateIntakeRequest)
}