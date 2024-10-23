package com.pada.medmaster.domain.model.treatment

class Medicament(
    val id: Long? = null,
    val name: String,
    val ingredients: List<Ingredient>? = mutableListOf()  // Initialize to empty list
)