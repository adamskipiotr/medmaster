package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.patient.CreatePatientRequest
import com.pada.medmaster.application.dto.request.treatment.AddIntakeDTO
import com.pada.medmaster.application.dto.request.treatment.CreateTreatmentRequest
import com.pada.medmaster.application.ports.`in`.AddIntakeUseCase
import com.pada.medmaster.application.ports.`in`.CreatePatientUseCase
import com.pada.medmaster.application.ports.`in`.AddPatientTreatment
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/patients")
@Tag(name = "Patients", description = "API for managing Patients")
class PatientController(
    private val createPatientUseCase: CreatePatientUseCase,
    private val addIntakeUseCase: AddIntakeUseCase,
    private val addTreatmentUseCase: AddPatientTreatment
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Create a new patient",
        description = "Adds a new patient.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The patient data to create.",
            required = true
        ),
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "201",
                description = "Patient successfully created."
            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "Invalid request data."
            )
        ]
    )
    fun createPatient(@RequestBody createPatientRequest: CreatePatientRequest) {
        createPatientUseCase.create(createPatientRequest)
    }

    @PostMapping("/{id}/treatments")
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
    fun addTreatment(@PathVariable id: Long, @RequestBody createTreatmentRequest: CreateTreatmentRequest) {
        addTreatmentUseCase.execute(id, createTreatmentRequest)
    }

    @PostMapping("/{id}/treatments/{treatmentID}/intakes")
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
    fun addIntake(
        @PathVariable id: Long,
        @PathVariable treatmentId: Long,
        @RequestBody addIntakeDTO: AddIntakeDTO
    ) {
        addIntakeUseCase.addIntake(id, addIntakeDTO)
    }


}
