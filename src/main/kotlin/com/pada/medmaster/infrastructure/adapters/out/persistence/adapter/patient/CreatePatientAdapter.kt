package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.application.ports.out.patient.CreatePatientPort
import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.springframework.stereotype.Component

@Component
class CreatePatientAdapter(val patientRepository: PatientRepository) : CreatePatientPort {

    override fun create(patient: Patient) {
        val patientEntity = of(patient)
        patientRepository.save(patientEntity)
    }
}