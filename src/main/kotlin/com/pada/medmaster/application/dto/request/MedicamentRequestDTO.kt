package com.pada.medmaster.application.dto.request

import org.jetbrains.annotations.NotNull

class MedicamentRequestDTO(
    val name: String,
    @NotNull
    val ingredients: List<IngredientRequestDTO> = mutableListOf()  // Initialize to empty list
)