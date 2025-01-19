package com.pada.medmaster.application.ports.out.patient


interface AddTreatmentToPatientPort {
    fun addTreatment(patientId: Long, treatmentId: Long)

}