package com.pada.medmaster.application.ports.`in`.ingredient

import com.pada.medmaster.application.dto.request.ingredient.CreateIngredientRequest

fun interface CreateIngredientUseCase {
    fun create(createIngredientRequest: CreateIngredientRequest)
}
