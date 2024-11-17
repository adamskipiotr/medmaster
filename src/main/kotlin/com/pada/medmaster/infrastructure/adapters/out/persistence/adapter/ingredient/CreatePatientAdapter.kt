package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.ingredient

import com.pada.medmaster.application.ports.out.CreateIngredientPort
import com.pada.medmaster.application.ports.out.CreateMedicamentPort
import com.pada.medmaster.application.ports.out.CreatePatientPort
import com.pada.medmaster.domain.model.medicament.Ingredient
import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.IngredientRepository
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.springframework.stereotype.Component

@Component
class CreateIngredientAdapter(val ingredientRepository: IngredientRepository) : CreateIngredientPort {

    override fun create(ingredient: Ingredient) {
        val ingredientEntity = of(ingredient)
        ingredientRepository.save(ingredientEntity)
    }
}