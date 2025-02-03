package com.pada.medmaster.domain.model.medicament

import com.pada.medmaster.domain.exception.IncompatibleMedicamentException
import com.pada.medmaster.domain.exception.PharmacyNotFoundException

// About bidirectional relationship:
// look at Domain Driven Design of Eric Evans - Chapter 5 - avoiding bidirectional relationship if there is no such need
class Medicament(
    val id: Long? = null,
    val name: String,
    var producer: String,
    var overdoseCounteractions: String,
    var ingredients: MutableList<Long> = mutableListOf(),  // Initialize to empty list
    var pharmacies: MutableList<Pharmacy> = mutableListOf(),
) {

    init {
        require(ingredients.isNotEmpty()) { "A medicament must have at least one ingredient." }
        require(overdoseCounteractions.isNotEmpty()) { "A medicament must have its overdose counteractions provided" }
    }

    fun validateSafeWithNewMedicament(newMedicament: Medicament) {
        val newMedicamentIngredients = newMedicament.ingredients
        val hasIncompatibleCombination = newMedicamentIngredients.any { it in ingredients }

        if (hasIncompatibleCombination) {
            throw IncompatibleMedicamentException("${newMedicament.name} can't be used together with ${this.name} - incompatible Ingredients")
        }
    }

    fun validateIsInVoivodeship(patientAddressVoivodeship: String?) {
        pharmacies.mapNotNull { it.address }
            .find { it.voivodeship == patientAddressVoivodeship }
            ?: throw PharmacyNotFoundException("No pharmacy with medicament $name found in voivodeship: $patientAddressVoivodeship")
    }


}
