package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.TreatmentRequestDTO

interface CreateTreatmentUseCase {
    fun createTreatment(treatmentRequestDTO: TreatmentRequestDTO)
}