package com.pada.medmaster.domain.model.medicament

// About bidirectional relationship:
// look at Domain Driven Design of Eric Evans - Chapter 5 - avoiding bidirectional relationship if there is no such need
class Medicament(
    val id: Long? = null,
    val name: String,
    var ingredients: MutableList<Ingredient> = mutableListOf()  // Initialize to empty list
    // add: producer, places of availability
) {

    init {
        require(ingredients.isNotEmpty()) { "A medicament must have at least one ingredient." }
    }

    fun addIngredients(ingredients: List<Ingredient>) {
        this.ingredients.addAll(ingredients)
    }
}
