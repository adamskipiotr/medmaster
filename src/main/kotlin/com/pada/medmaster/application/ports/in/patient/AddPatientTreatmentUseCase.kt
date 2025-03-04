package com.pada.medmaster.application.ports.`in`.patient

import com.pada.medmaster.application.dto.request.patient.CreateTreatmentRequest

fun interface AddPatientTreatmentUseCase {
    fun execute(id: Long, createTreatmentRequest: CreateTreatmentRequest)
}