package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.TreatmentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateTreatmentUseCase
import com.pada.medmaster.application.ports.`in`.GetAllTreatmentsUseCase
import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import com.pada.medmaster.domain.model.treatment.Treatment
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/treatments")
class TreatmentController(
    private val getTreatmentUseCase: GetTreatmentUseCase,
    private val getAllTreatmentsUseCase: GetAllTreatmentsUseCase,
    private val createTreatmentUseCase: CreateTreatmentUseCase
) {

    @GetMapping
    fun getAllTreatments(): List<Treatment> {
        return getAllTreatmentsUseCase.getAllTreatments()

    }

    @PostMapping
    fun createTreatment(@RequestBody treatmentRequestDTO: TreatmentRequestDTO) {
        createTreatmentUseCase.createTreatment(treatmentRequestDTO)
    }

    @PatchMapping("/{code}")
    fun getTreatmentByCode(@PathVariable code: String): Treatment {
        return getTreatmentUseCase.getTreatment(code)
    }
}
