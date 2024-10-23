package com.pada.medmaster.application.dto.request

class MedicamentRequestDTO(
    val name: String,
    val ingredients: List<IngredientRequestDTO> = mutableListOf()  // Initialize to empty list
)