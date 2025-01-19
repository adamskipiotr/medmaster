package com.pada.medmaster.domain.model.ingredient

import com.pada.medmaster.domain.model.medicament.Medicament

class Dose(
    val id: Long? = null,
    val name: String,
    val medicament: Medicament?,
    val parent: Dose? = null,
    val mutuallyExclusive: MutableList<Dose>? = mutableListOf(),
    val prohibitingCountries: List<Country> = mutableListOf()
    // Add: side effect information, excluded
) {
    fun addMutuallyExclusiveIngredient(ingredient: Dose) {
        mutuallyExclusive?.add(ingredient)
    }
}
