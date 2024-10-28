package com.pada.medmaster.domain.model.treatment

class Ingredient(
    val id: Long? = null,
    val name: String,
    var medicament: Medicament?,
    val parent: Ingredient? = null,
    val mutuallyExclusive: MutableList<Ingredient>? = mutableListOf(),
    val prohibitingCountries: List<Country>? = mutableListOf()
) {

    fun addMutuallyExclusiveIngredient(ingredient: Ingredient) {
        mutuallyExclusive?.add(ingredient)
    }

}