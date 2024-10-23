package com.pada.medmaster.application.dto.request

class IngredientRequestDTO(
    val name: String,
    val parent: IngredientRequestDTO? = null,
    val mutuallyExclusive: List<IngredientRequestDTO> = mutableListOf(),
    val prohibitingCountries: List<CountryRequestDTO> = mutableListOf()
)