package com.pada.medmaster.infrastructure.adapters.out.persistence.repository

import MedMasterApplicationTests
import com.pada.medmaster.MedMasterApplication
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MedMasterApplication::class])
@Sql(scripts = ["/ingredients.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
class IngredientRepositoryTest : MedMasterApplicationTests() {

    @Autowired
    private lateinit var ingredientRepository: IngredientRepository

    @Test
    fun shouldGetAllIngredient() {

        val result = ingredientRepository.findAll()

        assertEquals(5, result.size)
    }

    @Test
    fun shouldGetIngredientById() {

        val result = ingredientRepository.findById(100L)

        assertTrue(result.isPresent)
        assertEquals("Ingredient100", result.get().name)
    }

    @Test
    fun shouldThrowExceptionWhenIfHasNoEntityInDb() {

        val result = ingredientRepository.findById(999L)

        assertTrue(result.isEmpty)
    }
}