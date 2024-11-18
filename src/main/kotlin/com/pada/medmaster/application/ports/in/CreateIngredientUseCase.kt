package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.medicament.IngredientRequestDTO
import com.pada.medmaster.application.dto.request.patient.PatientRequestDTO

interface CreateIngredientUseCase {

    fun create(ingredientRequestDTO: IngredientRequestDTO)
}