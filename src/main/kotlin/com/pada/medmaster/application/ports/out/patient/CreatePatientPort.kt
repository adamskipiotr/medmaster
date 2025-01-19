package com.pada.medmaster.application.ports.out.patient

import com.pada.medmaster.domain.model.patient.Patient


interface CreatePatientPort {
    fun create(patient: Patient)

}