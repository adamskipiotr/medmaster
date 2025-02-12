package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.ingredient

import com.pada.medmaster.domain.model.ingredient.Country
import com.pada.medmaster.domain.model.ingredient.Ingredient
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.IngredientRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CreateIngredientAdapterTest {

    @Autowired
    private lateinit var ingredientRepository: IngredientRepository
    private val createIngredientAdapter = CreateIngredientAdapter(ingredientRepository)

    @Test
    fun should_do_when() {
        val country = Country(null, "Prohibiting Country")
        val ingredient = Ingredient(null, "New Ingredient", null, mutableListOf(), mutableListOf(country))

        createIngredientAdapter.create(ingredient)
    }
}