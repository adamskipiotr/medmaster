package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.MedicamentRequestDTO
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity

class MedicamentControllerIT : BaseIT() {

    // Uncommment later to enable integration tests
    /*
    @Test
    fun should_createMedicament_when_ingredientsAreProvided() {
        // given
        val medicamentRequestDTO = MedicamentRequestDTO("name", emptyList())

        //when
        val response: ResponseEntity<Unit> = testRestTemplate!!.exchange(
            "medicaments/",
            HttpMethod.POST,
            HttpEntity<MedicamentRequestDTO>(medicamentRequestDTO),
            Unit
        )
        //then
    }
    */
}