package com.pada.medmaster.infrastructure.adapters.`in`.rest

import MedMasterApplicationTests
import com.pada.medmaster.application.dto.request.ingredient.CreateIngredientRequest
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.IngredientRepository
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IngredientControllerIT : MedMasterApplicationTests() {

    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var ingredientRepository: IngredientRepository

    @Autowired
    private lateinit var medicamentRepository: MedicamentRepository

    @Test
    fun should_createIngredient_when_newPatientDataProvided() {
        // given
        val createIngredientRequest = CreateIngredientRequest(
            "Name", mutableListOf(),
        )

        //when
        val response: ResponseEntity<Unit> = restTemplate.exchange(
            "/ingredients",
            HttpMethod.POST,
            HttpEntity(createIngredientRequest),
            Unit::class.java
        )

        // then
        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(1, ingredientRepository.count())
    }
}