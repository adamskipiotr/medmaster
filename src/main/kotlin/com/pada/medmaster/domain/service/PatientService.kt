package com.pada.medmaster.domain.service

import com.pada.medmaster.application.dto.request.patient.PatientRequestDTO
import com.pada.medmaster.application.ports.`in`.CreatePatientUseCase
import com.pada.medmaster.application.ports.out.CreatePatientPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class PatientService(
    val createPatientPort: CreatePatientPort
) : CreatePatientUseCase {

    @Transactional
    override fun create(patientRequestDTO: PatientRequestDTO) {
        val patient = patientRequestDTO.toDomain()
        createPatientPort.create(patient)
    }
}