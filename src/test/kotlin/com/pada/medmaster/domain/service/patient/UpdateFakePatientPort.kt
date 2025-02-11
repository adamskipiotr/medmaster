package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.ports.out.patient.UpdatePatientPort
import com.pada.medmaster.domain.model.patient.Patient

class UpdateFakePatientPort() : UpdatePatientPort {

    override fun update(patient: Patient) {
        println("Update Patient Stub")
    }
}
