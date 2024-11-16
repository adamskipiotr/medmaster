package com.pada.medmaster.domain.model.medicament

import com.pada.medmaster.domain.model.pharmacy.Pharmacy
import org.springframework.boot.availability.ApplicationAvailability

// About bidirectional relationship:
// look at Domain Driven Design of Eric Evans - Chapter 5 - avoiding bidirectional relationship if there is no such need
class Medicament(
    val id: Long? = null,
    val name: String,
    var producer: String,
    var overdoseCounteractions: String,
    var ingredients: MutableList<Ingredient> = mutableListOf(),  // Initialize to empty list
    var pharmacies: MutableList<Pharmacy> = mutableListOf(),
    // add: producer, places of availability
) {

    init {
        require(ingredients.isNotEmpty()) { "A medicament must have at least one ingredient." }
        require(overdoseCounteractions.isNotEmpty()) {"A medicament must have its overdose counteractions provided"}
    }

    fun addIngredients(ingredients: List<Ingredient>) {
        this.ingredients.addAll(ingredients)
    }
}
