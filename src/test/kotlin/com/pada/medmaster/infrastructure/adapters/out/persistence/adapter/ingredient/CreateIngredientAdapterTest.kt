package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.ingredient

import com.pada.medmaster.MedMasterApplication
import com.pada.medmaster.domain.model.ingredient.Country
import com.pada.medmaster.domain.model.ingredient.Ingredient
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.IngredientRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MedMasterApplication::class])
class CreateIngredientAdapterTest {

    @Mock
    private lateinit var ingredientRepository: IngredientRepository
    @InjectMocks
    private lateinit var createIngredientAdapter: CreateIngredientAdapter

    @Test
    fun should_do_when() {
        val country = Country(null, "Prohibiting Country")
        val ingredient = Ingredient(null, "New Ingredient", null, mutableListOf(), mutableListOf(country))

        createIngredientAdapter.create(ingredient)
    }
}