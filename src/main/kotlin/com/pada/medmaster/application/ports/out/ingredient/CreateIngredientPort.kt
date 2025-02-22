package com.pada.medmaster.application.ports.out.ingredient

import com.pada.medmaster.domain.model.ingredient.Ingredient


fun interface CreateIngredientPort {
    fun create(newIngredient: Ingredient)
}