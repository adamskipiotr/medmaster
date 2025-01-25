package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.application.ports.out.patient.UpdatePatientPort
import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.springframework.stereotype.Component

@Component
class UpdatePatientAdapter(val patientRepository: PatientRepository) : UpdatePatientPort {

    override fun update(patient: Patient){
        val patientEntity = of(patient)
        patientRepository.save(patientEntity)
    }
}