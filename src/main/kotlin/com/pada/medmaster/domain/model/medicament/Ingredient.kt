package com.pada.medmaster.domain.model.medicament

class Ingredient(
    val id: Long? = null,
    val name: String,
    val medicament: Medicament?,
    val incompatibleIngredients: MutableList<Ingredient>? = mutableListOf(),
    val prohibitingCountries: MutableList<Country> = mutableListOf()
    // Add: side effect information, excluded
) {
    fun addIncompatibleIngredients(incompatibleIngredients: List<Ingredient>) {
        this.incompatibleIngredients?.addAll(incompatibleIngredients)
    }

    fun addProhibitingCountries(prohibitingCountries: List<Country>){
        this.prohibitingCountries.addAll(prohibitingCountries)
    }
}
