package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.domain.model.treatment.Treatment

interface GetTreatmentUseCase {
    fun getTreatment(code : String) : Treatment
}