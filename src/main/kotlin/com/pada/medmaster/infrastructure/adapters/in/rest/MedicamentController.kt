package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.MedicamentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateMedicamentUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/medicaments")
class MedicamentController(
    private val createMedicamentUseCase: CreateMedicamentUseCase
) {

    @PostMapping
    fun createTreatment(@RequestBody medicamentRequestDTO: MedicamentRequestDTO) {
        createMedicamentUseCase.create(medicamentRequestDTO)
    }

}
