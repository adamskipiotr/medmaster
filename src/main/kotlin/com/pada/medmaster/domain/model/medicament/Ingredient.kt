package com.pada.medmaster.domain.model.medicament

import com.pada.medmaster.domain.model.treatment.Country

class Ingredient(
    val id: Long? = null,
    val name: String,
    val medicament: Medicament?,
    val parent: Ingredient? = null,
    val mutuallyExclusive: MutableList<Ingredient>? = mutableListOf(),
    val prohibitingCountries: List<Country> = mutableListOf()
    // Add: side effect information, excluded
) {
    fun addMutuallyExclusiveIngredient(ingredient: Ingredient) {
        mutuallyExclusive?.add(ingredient)
    }
}
