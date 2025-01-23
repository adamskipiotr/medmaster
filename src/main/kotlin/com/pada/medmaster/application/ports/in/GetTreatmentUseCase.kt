package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.domain.model.patient.Treatment

interface GetTreatmentUseCase {
    fun execute(code: String): Treatment
}