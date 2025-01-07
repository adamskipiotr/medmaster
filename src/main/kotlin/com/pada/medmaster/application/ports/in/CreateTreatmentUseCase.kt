package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.treatment.TreatmentRequestDTO

interface CreateTreatmentUseCase {
    fun execute(treatmentRequestDTO: TreatmentRequestDTO)
}