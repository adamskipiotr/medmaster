package com.pada.medmaster.domain.service.ingredient

import com.pada.medmaster.application.dto.request.medicament.IngredientRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateIngredientUseCase
import com.pada.medmaster.application.ports.out.CreateIngredientPort
import com.pada.medmaster.domain.service.toDomain
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