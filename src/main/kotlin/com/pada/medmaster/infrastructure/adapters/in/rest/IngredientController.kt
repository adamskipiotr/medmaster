package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.medicament.CreateIngredientRequest
import com.pada.medmaster.application.ports.`in`.CreateIngredientUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ingredients")
class IngredientController(
    private val createIngredientUseCase: CreateIngredientUseCase
) {

    @PostMapping
    fun createTreatment(@RequestBody createIngredientRequest: CreateIngredientRequest) {
        createIngredientUseCase.create(createIngredientRequest)
    }
}
