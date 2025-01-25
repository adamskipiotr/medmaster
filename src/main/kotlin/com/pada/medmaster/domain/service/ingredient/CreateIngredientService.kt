package com.pada.medmaster.domain.service.ingredient

import com.pada.medmaster.application.dto.request.medicament.CreateIngredientRequest
import com.pada.medmaster.application.ports.`in`.medicament.CreateIngredientUseCase
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class CreateIngredientService(
) : CreateIngredientUseCase {

    @Transactional
    override fun create(createIngredientRequest: CreateIngredientRequest) {
//        val newIngredient = createIngredientRequest.toDomain()
//        val incompatibleIngredients =  getIngredientsPort.get(createIngredientRequest.mutuallyExclusive.orEmpty())
//        newIngredient.addIncompatibleIngredients(incompatibleIngredients)
//        createIngredientPort.create(newIngredient)
    }
}