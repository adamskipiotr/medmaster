package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.ports.`in`.AddIntakeUseCase
import com.pada.medmaster.application.ports.`in`.AddPatientTreatment
import com.pada.medmaster.application.ports.`in`.GetAllTreatmentsUseCase
import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/treatments")
@Tag(name = "Treatments", description = "API for managing Treatments")
class TreatmentController(
    private val getTreatmentUseCase: GetTreatmentUseCase,
    private val getAllTreatmentsUseCase: GetAllTreatmentsUseCase,
    private val addPatientTreatment: AddPatientTreatment,
    private val addIntakeUseCase: AddIntakeUseCase
) {



}
