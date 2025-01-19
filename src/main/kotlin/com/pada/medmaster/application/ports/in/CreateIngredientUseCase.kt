package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.application.dto.request.medicament.CreateIngredientRequest

interface CreateIngredientUseCase {

    fun create(createIngredientRequest: CreateIngredientRequest)
}