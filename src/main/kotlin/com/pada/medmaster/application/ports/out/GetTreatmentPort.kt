package com.pada.medmaster.application.ports.out

import com.pada.medmaster.domain.model.treatment.Treatment

interface GetTreatmentPort {
    fun getTreatment(code: String): Treatment

}