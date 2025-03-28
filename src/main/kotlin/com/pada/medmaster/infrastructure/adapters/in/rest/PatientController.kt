package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.patient.*
import com.pada.medmaster.application.ports.`in`.patient.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/patients")
@Tag(name = "Patients", description = "API for managing Patients")
class PatientController(
    private val createPatientUseCase: CreatePatientUseCase,
    private val addIntakeUseCase: AddIntakeUseCase,
    private val reportIntakeUseCase: ReportIntakeUseCase,

    private val addTreatmentUseCase: AddPatientTreatmentUseCase,
    private val addMedicalProcedureUseCase: AddMedicalProcedureUseCase
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
    @ResponseStatus(HttpStatus.CREATED)
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

    @PostMapping("/{id}/treatments/{treatmentId}/intakes")
    @ResponseStatus(HttpStatus.CREATED)
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
        @RequestBody createIntakeRequest: CreateIntakeRequest
    ): ResponseEntity<String> {
        addIntakeUseCase.addIntake(id, treatmentId, createIntakeRequest)                    // TODO: Consider other ways of handling errors
        return ResponseEntity.status(HttpStatus.CREATED).build()                            //  like in https://theboreddev.com/exception-handling-in-spring-services/
    }                                                                                       //  or in https://kotlincraft.dev/articles/error-handling-best-practices-in-spring-boot-with-kotlin


    @PutMapping("/{id}/treatments/{treatmentId}/intakes/{intakeId}/report-intake")
    @ResponseStatus(HttpStatus.CREATED)
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
    fun reportIntake(
        @PathVariable id: Long,
        @PathVariable treatmentId: Long,
        @PathVariable intakeId: Long,
        @RequestBody reportIntakeRequest: ReportIntakeRequest
    ): ResponseEntity<String> {
        reportIntakeUseCase.report(id, treatmentId, intakeId, reportIntakeRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/{id}/treatments/{treatmentId}/medical-procedures")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Add medical procedure to an existing Treatment",
        description = "Adds a new medical procedure to a treatment.",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "The medical procedure data to add to treatment.",
            required = true
        ),
        responses = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "201",
                description = "Medical Procedure successfully added."
            ),
            io.swagger.v3.oas.annotations.responses.ApiResponse(
                responseCode = "400",
                description = "Invalid request data."
            )
        ]
    )
    fun addMedicalProcedure(
        @PathVariable id: Long,
        @PathVariable treatmentId: Long,
        @RequestBody createMedicalProcedureRequest: CreateMedicalProcedureRequest
    ): ResponseEntity<String> {
        addMedicalProcedureUseCase.add(id, treatmentId, createMedicalProcedureRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
