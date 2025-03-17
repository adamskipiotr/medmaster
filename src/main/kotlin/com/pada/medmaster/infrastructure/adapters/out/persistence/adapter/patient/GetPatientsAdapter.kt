package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.application.ports.out.patient.GetPatientsPort
import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.springframework.stereotype.Component

@Component
class GetPatientsAdapter(val patientRepository: PatientRepository) : GetPatientsPort {

    override fun get(): List<Patient> {
        return patientRepository.findAll().map { it.asDomain() }
    }

}