package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.MedicamentRequestDTO

interface CreateMedicamentUseCase {

    fun create(medicamentRequestDTO: MedicamentRequestDTO)
}