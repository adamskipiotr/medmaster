package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.patient.PatientRequestDTO

interface CreatePatientUseCase {

    fun create(patientRequestDTO: PatientRequestDTO)
}