package com.pada.medmaster.infrastructure.adapters.`in`.rest

import MedMasterApplicationTests
import com.pada.medmaster.application.dto.request.IngredientRequestDTO
import com.pada.medmaster.application.dto.request.MedicamentRequestDTO
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
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
class MedicamentControllerIT : MedMasterApplicationTests(){

    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var medicamentRepository: MedicamentRepository


    @Test
    fun should_createMedicament_when_ingredientsAreProvided() {
        // given
        val ingredientRequestDTO = IngredientRequestDTO("name", null, null, emptyList())
        val medicamentRequestDTO = MedicamentRequestDTO("name", listOf(ingredientRequestDTO))

        //when
        val response: ResponseEntity<Unit> = restTemplate.exchange(
            "/medicaments",
            HttpMethod.POST,
            HttpEntity(medicamentRequestDTO),
            Unit::class.java
        )

        // then
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(1, medicamentRepository.count())
    }
}