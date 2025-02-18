package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.ingredient

import com.pada.medmaster.application.ports.out.ingredient.GetIngredientsPort
import com.pada.medmaster.domain.model.ingredient.Ingredient
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.IngredientRepository
import org.springframework.stereotype.Component

@Component
class GetIngredientsAdapter(private val ingredientRepository: IngredientRepository) : GetIngredientsPort {

    override fun get(ids: List<Long>): List<Ingredient> {
        val ingredients = ingredientRepository.findAllById(ids)
        return ingredients.map { it.asDomain() }
    }
}