package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.MedicamentRequestDTO
import com.pada.medmaster.application.dto.request.TreatmentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateMedicamentUseCase
import com.pada.medmaster.application.ports.`in`.CreateTreatmentUseCase
import com.pada.medmaster.application.ports.`in`.GetAllTreatmentsUseCase
import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import com.pada.medmaster.domain.model.treatment.Treatment
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/medicaments")
class MedicamentController(
    private val createMedicamentUseCase: CreateMedicamentUseCase
) {

    @PostMapping
    fun createTreatment(@RequestBody medicamentRequestDTO: MedicamentRequestDTO) {
        createMedicamentUseCase.createMedicament(medicamentRequestDTO)
    }

}
