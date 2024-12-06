package com.pada.medmaster.domain.model.medicament

import com.pada.medmaster.domain.model.pharmacy.Pharmacy

// About bidirectional relationship:
// look at Domain Driven Design of Eric Evans - Chapter 5 - avoiding bidirectional relationship if there is no such need
class Medicament(
    val id: Long? = null,
    val name: String,
    var producer: String,
    var overdoseCounteractions: String,
    var ingredientsIds: MutableList<Long> = mutableListOf(),  // Initialize to empty list
    var pharmacies: MutableList<Pharmacy> = mutableListOf(),
) {

    init {
        require(ingredientsIds.isNotEmpty()) { "A medicament must have at least one ingredient." }
        require(overdoseCounteractions.isNotEmpty()) { "A medicament must have its overdose counteractions provided" }
    }

    fun addIngredients(ingredients: List<Ingredient>) {
        val ingredientsIds = ingredients.mapNotNull { it.id }.toMutableList()
        this.ingredientsIds.addAll(ingredientsIds)
    }
}
