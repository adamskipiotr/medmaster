package com.pada.medmaster.application.ports.out.treatment

import com.pada.medmaster.domain.model.treatment.Treatment

interface GetTreatmentPort {
    fun get(id: Long): Treatment

}