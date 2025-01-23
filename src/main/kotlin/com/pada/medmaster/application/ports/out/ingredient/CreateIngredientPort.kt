package com.pada.medmaster.application.ports.out.ingredient

import com.pada.medmaster.domain.model.medicament.Ingredient


interface CreateIngredientPort {
    fun create(ingredient: Ingredient)

}