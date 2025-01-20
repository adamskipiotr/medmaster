package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.treatment.AddIntakeDTO

interface AddIntakeUseCase {
    fun addIntake(id: Long, addIntakeDTO: AddIntakeDTO)
}