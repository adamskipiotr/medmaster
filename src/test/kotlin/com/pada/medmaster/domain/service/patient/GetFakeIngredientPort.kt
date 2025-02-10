package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.ports.out.ingredient.GetIngredientsPort
import com.pada.medmaster.domain.model.ingredient.Ingredient

class GetFakeIngredientPort() : GetIngredientsPort {

    private val ingredients = mutableMapOf(
        1L to Ingredient(1L,"Name", null, mutableListOf(), mutableListOf())
    )

    override fun get(ids: List<Long>): List<Ingredient> {
        val foundIngredients = ids.mapNotNull { ingredients[it] }

        if (foundIngredients.size != ids.size) {
            throw NoSuchElementException("Some ingredients not found: ${ids - ingredients.keys}")
        }

        return foundIngredients
    }
}
