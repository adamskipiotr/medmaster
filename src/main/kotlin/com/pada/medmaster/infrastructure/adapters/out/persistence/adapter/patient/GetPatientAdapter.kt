package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.application.ports.out.patient.GetPatientPort
import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.springframework.stereotype.Component

@Component
class GetPatientAdapter(val patientRepository: PatientRepository) : GetPatientPort {
    override fun get(id: Long): Patient {
        return patientRepository.findById(id).asDomain()
    }

}