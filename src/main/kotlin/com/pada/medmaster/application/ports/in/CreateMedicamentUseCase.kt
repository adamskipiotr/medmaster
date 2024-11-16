package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.medicament.MedicamentRequestDTO

interface CreateMedicamentUseCase {

    fun create(medicamentRequestDTO: MedicamentRequestDTO)
}