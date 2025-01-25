package com.pada.medmaster.application.ports.out.patient

import com.pada.medmaster.domain.model.patient.Treatment

interface GetTreatmentPort {
    fun get(id: Long): Treatment

}