package com.pada.medmaster.domain.service

import com.pada.medmaster.application.dto.request.medicament.IngredientRequestDTO
import com.pada.medmaster.application.dto.request.patient.PatientRequestDTO
import com.pada.medmaster.application.dto.request.treatment.TreatmentRequestDTO
import com.pada.medmaster.application.ports.`in`.*
import com.pada.medmaster.application.ports.out.*
import com.pada.medmaster.domain.model.medicament.Ingredient
import com.pada.medmaster.domain.model.treatment.Treatment
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class IngredientService(
    val createIngredientPort: CreateIngredientPort
) : CreateIngredientUseCase {

    @Transactional
    override fun create(ingredientRequestDTO: IngredientRequestDTO) {
        val ingredient = ingredientRequestDTO.toDomain()
        createIngredientPort.create(ingredient)
    }
}