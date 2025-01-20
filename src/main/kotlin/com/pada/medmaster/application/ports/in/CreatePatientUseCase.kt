package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.patient.CreatePatientRequest

interface CreatePatientUseCase {

    fun create(createPatientRequest: CreatePatientRequest)
}