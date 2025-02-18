package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.ingredient

import com.pada.medmaster.MedMasterApplication
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.ingredient.IngredientEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.IngredientRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MedMasterApplication::class])
class GetIngredientsAdapterTest {

    @Mock
    private lateinit var ingredientRepository: IngredientRepository
    @InjectMocks
    private lateinit var getIngredientsAdapter: GetIngredientsAdapter

    @Test
    fun shouldGetIngredientsMappedToDomain() {
        val ingredientEntities = createIngredientEntities()
        whenever(
            ingredientRepository.findAllById(
                listOf(
                    100L,
                    101L
                )
            )
        ).thenReturn(ingredientEntities) // no when in Kotlin

        //when
        val result = getIngredientsAdapter.get(listOf(100, 101))
        //then
        assertEquals(2, result.size)
        assertEquals("Ingredient100", result.first().name)   // refactor assertions
    }

    private fun createIngredientEntities(): List<IngredientEntity> {
        return listOf(
            IngredientEntity().apply {
                id = 100L
                name = "Ingredient100"
            },
            IngredientEntity().apply {
                id = 101L
                name = "Ingredient101"
            })
    }
}