package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.ingredient

import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament.CreateMedicamentAdapter
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.IngredientRepository
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class GetIngredientsAdapterTest {

    @Autowired
    private lateinit var ingredientRepository: IngredientRepository
    private val getIngredientsAdapter = GetIngredientsAdapter(ingredientRepository)

    @Test
    fun should_returnSingleIngredient_whenOnlyOneIdHasEntityInDb() {

        val result = getIngredientsAdapter.get(listOf(100L))
    }

    @Test
    fun should_getMultipleIngredients_whenAllIdsHaveEntitiesInDb() {

        val result = getIngredientsAdapter.get(listOf(100L, 101L, 102L))
    }

    @Test
    fun should_getEmptyList_whenNoEntityFoundForGivenIdsInDb() {

        val result = getIngredientsAdapter.get(listOf(999L, 998L, 997L))
    }
}