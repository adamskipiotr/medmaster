package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.medicament.IngredientRequestDTO

interface CreateIngredientUseCase {

    fun create(ingredientRequestDTO: IngredientRequestDTO)
}