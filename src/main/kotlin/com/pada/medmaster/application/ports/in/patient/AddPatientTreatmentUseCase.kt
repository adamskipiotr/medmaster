package com.pada.medmaster.application.ports.`in`.patient

import com.pada.medmaster.application.dto.request.treatment.CreateTreatmentRequest

interface AddPatientTreatmentUseCase {
    fun execute(id: Long, createTreatmentRequest: CreateTreatmentRequest)
}