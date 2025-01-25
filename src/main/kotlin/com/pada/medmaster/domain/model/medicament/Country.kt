package com.pada.medmaster.domain.model.medicament

class Country(
    val id: Long? = null,
    val name: String,
    val prohibitedIngredients: Set<Ingredient>? = HashSet()
)
