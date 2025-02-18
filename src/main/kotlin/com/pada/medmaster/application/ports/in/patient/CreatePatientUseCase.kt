package com.pada.medmaster.application.ports.`in`.patient

import com.pada.medmaster.application.dto.request.patient.CreatePatientRequest

fun interface CreatePatientUseCase {
    fun create(createPatientRequest: CreatePatientRequest)
}