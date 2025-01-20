package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.treatment.CreateTreatmentRequest

interface CreateTreatmentUseCase {
    fun execute(createTreatmentRequest: CreateTreatmentRequest)
}