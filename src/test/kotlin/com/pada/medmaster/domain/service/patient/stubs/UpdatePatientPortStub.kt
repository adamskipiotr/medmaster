package com.pada.medmaster.domain.service.patient.stubs

import com.pada.medmaster.application.ports.out.patient.UpdatePatientPort
import com.pada.medmaster.domain.model.patient.Patient

class UpdatePatientPortStub() : UpdatePatientPort {

    override fun update(patient: Patient) {
        println("Update Patient Stub")
    }
}
