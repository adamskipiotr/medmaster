package com.pada.medmaster.domain.model.ingredient

import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.domain.exception.IngredientProhibitedInPatientCountryException

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

    fun addProhibitingCountries(prohibitingCountries: List<Country>) {
        this.prohibitingCountries.addAll(prohibitingCountries)
    }

    fun isAllowedIn(patientAddressCountry: String?) {
        if (prohibitingCountries.any { it.name == patientAddressCountry }) {
            throw IngredientProhibitedInPatientCountryException("Ingredient $name is prohibited in $patientAddressCountry")
        }
    }
}
