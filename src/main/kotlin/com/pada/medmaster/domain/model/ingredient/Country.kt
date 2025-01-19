package com.pada.medmaster.domain.model.ingredient

class Country(
    val id: Long? = null,
    val name: String,
    val prohibitedIngredients: Set<Ingredient>? = HashSet()
)
