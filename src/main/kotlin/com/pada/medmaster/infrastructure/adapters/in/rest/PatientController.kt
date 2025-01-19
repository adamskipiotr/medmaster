package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.patient.PatientRequestDTO
import com.pada.medmaster.application.ports.`in`.CreatePatientUseCase
import com.pada.medmaster.domain.model.treatment.Treatment
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/patients")
@Tag(name = "Patients", description = "API for managing Patients")
class PatientController(
    private val createPatientUseCase: CreatePatientUseCase
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
    fun createPatient(@RequestBody patientRequestDTO: PatientRequestDTO) {
        createPatientUseCase.create(patientRequestDTO)
    }


}
