package com.pada.medmaster.domain.model.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IngredientEntity

class Medicament(
    var id: Long = 0,
    val name: String,
    val ingredients: List<Ingredient> = mutableListOf()  // Initialize to empty list
)