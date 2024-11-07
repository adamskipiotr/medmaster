package com.pada.medmaster.application.dto.request

data class IngredientRequestDTO(
    val name: String,
    val parentId: Long? = null,
    val mutuallyExclusive: List<IngredientRequestDTO>? = emptyList(),
    val prohibitingCountries: List<CountryRequestDTO>
)