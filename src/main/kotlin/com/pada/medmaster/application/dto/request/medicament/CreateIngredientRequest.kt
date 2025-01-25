package com.pada.medmaster.application.dto.request.medicament

import com.pada.medmaster.application.dto.request.treatment.CountryRequest

data class CreateIngredientRequest(
    val name: String,
    val mutuallyExclusive: List<Long>? = emptyList(),
    val prohibitingCountries: List<CountryRequest>? = emptyList()
)