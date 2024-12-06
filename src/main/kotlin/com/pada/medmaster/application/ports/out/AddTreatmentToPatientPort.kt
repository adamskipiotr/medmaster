package com.pada.medmaster.application.ports.out

import com.pada.medmaster.domain.model.patient.Patient


interface AddTreatmentToPatientPort {
    fun addTreatment(patientId: Long, treatmentId: Long)

}