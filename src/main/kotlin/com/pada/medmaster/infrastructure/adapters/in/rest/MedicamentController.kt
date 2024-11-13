package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.MedicamentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateMedicamentUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/medicaments")
class MedicamentController(
    private val createMedicamentUseCase: CreateMedicamentUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createTreatment(@RequestBody medicamentRequestDTO: MedicamentRequestDTO) {
        createMedicamentUseCase.create(medicamentRequestDTO)
    }

}
