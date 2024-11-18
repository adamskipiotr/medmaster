package com.pada.medmaster.application.ports.out

import com.pada.medmaster.domain.model.medicament.Ingredient
import com.pada.medmaster.domain.model.patient.Patient


interface CreateIngredientPort {
    fun create(ingredient: Ingredient)

}