package com.pada.medmaster.application.ports.out.treatment

import com.pada.medmaster.domain.model.patient.Treatment

interface GetTreatmentPort {
    fun get(id: Long): Treatment

}