package com.pada.medmaster.application.ports.`in`.medicament

import com.pada.medmaster.application.dto.request.medicament.CreateIngredientRequest

interface CreateIngredientUseCase {

    fun create(createIngredientRequest: CreateIngredientRequest)
}