package com.pada.medmaster.application.dto.request.medicament

import org.jetbrains.annotations.NotNull

class MedicamentRequestDTO(
    val name: String,
    val producer: String,
    val overdoseCounteraction: String,
    @NotNull
    val ingredientsIds: MutableList<CreateIngredientRequest> = mutableListOf(),  // Initialize to empty list
    val pharmacies: List<PharmacyDTO> = mutableListOf()
)