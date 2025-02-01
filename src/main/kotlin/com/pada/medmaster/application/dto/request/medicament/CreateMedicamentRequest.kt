package com.pada.medmaster.application.dto.request.medicament

import org.jetbrains.annotations.NotNull

class CreateMedicamentRequest(
    val name: String,
    val producer: String,
    val overdoseCounteraction: String,
    @NotNull
    val ingredients: MutableList<Long> = mutableListOf(),  // Initialize to empty list
    val pharmacies: List<CreatePharmacyRequest> = mutableListOf()
)