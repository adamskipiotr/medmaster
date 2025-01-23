package com.pada.medmaster.application.ports.out.treatment

import com.pada.medmaster.domain.model.patient.Treatment

interface UpdateTreatmentPort {
    fun update(treatment: Treatment)

}