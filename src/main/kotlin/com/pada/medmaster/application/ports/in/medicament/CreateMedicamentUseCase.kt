package com.pada.medmaster.application.ports.`in`.medicament

import com.pada.medmaster.application.dto.request.medicament.CreateMedicamentRequest

interface CreateMedicamentUseCase {

    fun create(createMedicamentRequest: CreateMedicamentRequest)
}