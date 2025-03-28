package com.pada.medmaster.application.dto.request.ingredient

import com.pada.medmaster.application.dto.request.patient.CountryRequest

data class CreateIngredientRequest(
    val name: String,
    val mutuallyExclusive: List<Long>? = emptyList(),
    val prohibitingCountries: List<CountryRequest>? = emptyList()
)