package com.pada.medmaster.domain.model.treatment

import com.pada.medmaster.domain.model.medicament.Ingredient

class Country(
    val id: Long? = null,
    val name: String,
    val prohibitedIngredients: Set<Ingredient>? = HashSet()
)
