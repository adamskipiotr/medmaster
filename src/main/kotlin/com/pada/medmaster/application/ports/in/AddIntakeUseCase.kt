package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.treatment.AddIntakeDTO
import com.pada.medmaster.application.dto.request.treatment.TreatmentRequestDTO

interface AddIntakeUseCase {
    fun addIntake(id: Long, addIntakeDTO: AddIntakeDTO)
}