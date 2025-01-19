package com.pada.medmaster.application.dto.request.medicament

import com.pada.medmaster.application.dto.request.treatment.CountryRequestDTO

data class CreateIngredientRequest(
    val name: String,
    val mutuallyExclusive: List<Long>? = emptyList(),
    val prohibitingCountries: List<CountryRequestDTO>? = emptyList()
)