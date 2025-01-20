package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.treatment.AddIntakeDTO
import com.pada.medmaster.application.dto.request.treatment.CreateTreatmentRequest
import com.pada.medmaster.application.ports.`in`.AddIntakeUseCase
import com.pada.medmaster.application.ports.`in`.CreateTreatmentUseCase
import com.pada.medmaster.application.ports.`in`.GetAllTreatmentsUseCase
import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import com.pada.medmaster.domain.model.treatment.Treatment
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/treatments")
@Tag(name = "Treatments", description = "API for managing Treatments")
class TreatmentController(
    private val getTreatmentUseCase: GetTreatmentUseCase,
    private val getAllTreatmentsUseCase: GetAllTreatmentsUseCase,
    private val createTreatmentUseCase: CreateTreatmentUseCase,
    private val addIntakeUseCase: AddIntakeUseCase
) {

    @GetMapping
    @Operation(
        summary = "Get all treatments",
        description = "Returns a list of all treatments.",
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "List of treatments successfully retrieved.",
                content = [io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = io.swagger.v3.oas.annotations.media.Schema(
                        implementation = Treatment::class
                    )
                )]
            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "500",
                description = "Internal server error."
            )
        ]
    )
    fun getAllTreatments(): List<Treatment> {
        return getAllTreatmentsUseCase.execute()

    }

    @PostMapping
    @Operation(
        summary = "Create a new treatment",
        description = "Adds a new treatment.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The treatment data to create.",
            required = true
        ),
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "201",
                description = "Treatment successfully created."
            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "Invalid request data."
            )
        ]
    )
    fun createTreatment(@RequestBody createTreatmentRequest: CreateTreatmentRequest) {
        createTreatmentUseCase.execute(createTreatmentRequest)
    }

    @PostMapping("/{id}/intakes")
    @Operation(
        summary = "Add intake to an existing Treatment",
        description = "Adds a new intake to a treatment.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The intake data to add to treatment.",
            required = true
        ),
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "201",
                description = "Intake successfully added."
            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "Invalid request data."
            )
        ]
    )
    fun addIntake(@PathVariable id: Long,
                  @RequestBody addIntakeDTO: AddIntakeDTO) {
        addIntakeUseCase.addIntake(id, addIntakeDTO)
    }

    @PatchMapping("/{code}")
    @Operation(
        summary = "Get Treatment by Code",
        description = "Returns single Treatment found by code.",
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "200",
                description = "Treatment successfully retrieved.",
                content = [io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json",
                    schema = io.swagger.v3.oas.annotations.media.Schema(
                        implementation = Treatment::class
                    )
                )]
            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "500",
                description = "Internal server error."
            )
        ]
    )
    fun getTreatmentByCode(@PathVariable @Parameter(name = "Code", description = "Code of searched Treatment") code: String): Treatment {
        return getTreatmentUseCase.execute(code)
    }
}
