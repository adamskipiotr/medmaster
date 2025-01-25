package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.medicament.CreateMedicamentRequest
import com.pada.medmaster.application.ports.`in`.medicament.CreateMedicamentUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/medicaments")
@Tag(name = "Medicaments", description = "API for managing Medicaments")
class MedicamentController(
    private val createMedicamentUseCase: CreateMedicamentUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Create a new medicament",
        description = "Adds a new medicament.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The medicament data to create.",
            required = true
        ),
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "201",
                description = "Medicament successfully created."
            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "Invalid request data."
            )
        ]
    )
    fun createTreatment(@RequestBody createMedicamentRequest: CreateMedicamentRequest) {
        createMedicamentUseCase.create(createMedicamentRequest)
    }
}
