package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.application.ports.out.AddTreatmentToPatientPort
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.springframework.stereotype.Component

@Component
class AddTreatmentToPatientAdapter(val patientRepository: PatientRepository) : AddTreatmentToPatientPort {

    override fun addTreatment(patientId: Long, treatmentId: Long) {
        val patient = patientRepository.findById(patientId)
        patient.addTreatment(treatmentId)
        patientRepository.save(patient)
    }
}