package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.medicament.CreateIngredientRequest
import com.pada.medmaster.application.ports.`in`.CreateIngredientUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ingredients", description = "API for managing Ingredients")
class IngredientController(
    private val createIngredientUseCase: CreateIngredientUseCase
) {

    @PostMapping
    @Operation(
        summary = "Create a new ingredient",
        description = "Adds a new ingredient with its incompatibilities.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The ingredient data to create, including incompatible ingredients.",
            required = true
        ),
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "201",
                description = "Ingredient successfully created."
            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "Invalid request data."
            )
        ]
    )
    fun createTreatment(@RequestBody @Parameter(name = "Request body for new Ingredient") createIngredientRequest: CreateIngredientRequest) {
        createIngredientUseCase.create(createIngredientRequest)
    }
}
