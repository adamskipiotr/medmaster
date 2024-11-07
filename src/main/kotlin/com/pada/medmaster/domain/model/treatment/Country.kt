package com.pada.medmaster.domain.model.treatment

class Country(
    val id: Long? = null,
    val name: String,
    val prohibitedIngredients: Set<Ingredient>? = HashSet()
)
