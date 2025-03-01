package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.application.ports.out.patient.UpdatePatientPort
import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.springframework.stereotype.Component

@Component
class UpdatePatientAdapter(val patientRepository: PatientRepository) : UpdatePatientPort {

    override fun update(patient: Patient){
        val patientEntity = patientRepository.findById(patient.id!!) // TODO: Updating patient's address should result in re-validating medicaments (if they are allowed & available in new country)
                                                                   // TODO Refactor to throw well described exception here
        patientEntity.updateFromDomain(patient)

        patientRepository.save(patientEntity)
    }
}