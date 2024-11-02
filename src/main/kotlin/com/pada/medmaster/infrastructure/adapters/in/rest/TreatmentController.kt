package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.IntakeDateRequestDTO
import com.pada.medmaster.application.dto.request.TreatmentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateTreatmentUseCase
import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import com.pada.medmaster.domain.model.treatment.Treatment
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/treatments")
class TreatmentController(
    private val getTreatmentUseCase: GetTreatmentUseCase,
    private val createTreatmentUseCase: CreateTreatmentUseCase
) {

    @GetMapping
    fun getAllTreatments(): List<Treatment> {
        return Collections.emptyList()

    }

    @PostMapping
    fun createTreatment(@RequestBody treatmentRequestDTO: TreatmentRequestDTO) {
        createTreatmentUseCase.createTreatment(treatmentRequestDTO)
    }

    @PostMapping("/test")
    fun createTreatmentooo(@RequestBody intakeDateRequestDTO: IntakeDateRequestDTO) {
    }

    @PatchMapping("/{code}")
    fun getTreatmentByCode(@PathVariable code: String): Treatment {
        return getTreatmentUseCase.getTreatment(code)
    }
}
