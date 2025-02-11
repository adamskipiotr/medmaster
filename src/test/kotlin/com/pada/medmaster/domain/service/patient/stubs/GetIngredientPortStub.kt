package com.pada.medmaster.domain.service.patient.stubs

import com.pada.medmaster.application.ports.out.ingredient.GetIngredientsPort
import com.pada.medmaster.domain.model.ingredient.Country
import com.pada.medmaster.domain.model.ingredient.Ingredient

class GetIngredientPortStub() : GetIngredientsPort {

    private val prohibitingCountry = Country(1L, "Country", mutableSetOf())
    private val incompatibleIngredient = Ingredient(3L, "Third Name", null, mutableListOf(), mutableListOf())
    private val ingredients = mutableMapOf(
        1L to Ingredient(1L, "Name", null, mutableListOf(), mutableListOf()),
        2L to Ingredient(2L, "Second Name", null, mutableListOf(), mutableListOf(prohibitingCountry)),
        3L to incompatibleIngredient,
        4L to Ingredient(4L, "Fourth Name", null, mutableListOf(incompatibleIngredient), mutableListOf())

    )

    override fun get(ids: List<Long>): List<Ingredient> {
        val foundIngredients = ids.mapNotNull { ingredients[it] }

        if (foundIngredients.size != ids.size) {
            throw NoSuchElementException("Some ingredients not found: ${ids - ingredients.keys}")
        }
        return foundIngredients
    }
}
