package com.pada.medmaster.domain.model.treatment

// About bidirectional relationship:
// look at Domain Driven Design of Eric Evans - Chapter 5 - avoiding bidirectional relationship if there is no such need
class Medicament(
    val id: Long? = null,
    val name: String,
    private var ingredients: MutableList<Ingredient>? = mutableListOf()  // Initialize to empty list
) {
    fun addIngredients(ingredients: List<Ingredient>) {
        this.ingredients?.addAll(ingredients)
    }
}