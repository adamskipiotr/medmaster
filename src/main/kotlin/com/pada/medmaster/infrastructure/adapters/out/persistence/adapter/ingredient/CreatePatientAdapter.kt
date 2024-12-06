package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.ingredient

import com.pada.medmaster.application.ports.out.CreateIngredientPort
import com.pada.medmaster.domain.model.medicament.Ingredient
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.IngredientRepository
import org.springframework.stereotype.Component

@Component
class CreateIngredientAdapter(val ingredientRepository: IngredientRepository) : CreateIngredientPort {

    override fun create(ingredient: Ingredient) {
        val ingredientEntity = of(ingredient)
        ingredientRepository.save(ingredientEntity)
    }
}