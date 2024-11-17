package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.medicament.IngredientRequestDTO
import com.pada.medmaster.application.dto.request.treatment.TreatmentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateIngredientUseCase
import com.pada.medmaster.application.ports.`in`.CreateTreatmentUseCase
import com.pada.medmaster.application.ports.`in`.GetAllTreatmentsUseCase
import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import com.pada.medmaster.domain.model.treatment.Treatment
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/ingredients")
class IngredientController(
    private val createIngredientUseCase: CreateIngredientUseCase
) {

    @PostMapping
    fun createTreatment(@RequestBody ingredientRequestDTO: IngredientRequestDTO) {
        createIngredientUseCase.create(ingredientRequestDTO)
    }
}
