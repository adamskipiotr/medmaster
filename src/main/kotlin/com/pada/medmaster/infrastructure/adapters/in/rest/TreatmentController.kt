package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.treatment.AddIntakeDTO
import com.pada.medmaster.application.dto.request.treatment.TreatmentRequestDTO
import com.pada.medmaster.application.ports.`in`.AddIntakeUseCase
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
    private val createTreatmentUseCase: CreateTreatmentUseCase,
    private val addIntakeUseCase: AddIntakeUseCase
) {

    @GetMapping
    fun getAllTreatments(): List<Treatment> {
        return getAllTreatmentsUseCase.execute()

    }

    @PostMapping
    fun createTreatment(@RequestBody treatmentRequestDTO: TreatmentRequestDTO) {
        createTreatmentUseCase.execute(treatmentRequestDTO)
    }

    @PostMapping("/{id}/intakes")
    fun addIntake(@PathVariable id: Long, @RequestBody addIntakeDTO: AddIntakeDTO) {
        addIntakeUseCase.addIntake(id, addIntakeDTO)
    }

    @PatchMapping("/{code}")
    fun getTreatmentByCode(@PathVariable code: String): Treatment {
        return getTreatmentUseCase.execute(code)
    }
}
