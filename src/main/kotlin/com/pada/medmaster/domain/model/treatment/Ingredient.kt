package com.pada.medmaster.domain.model.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.CountryEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IngredientEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.MedicamentEntity

class Ingredient(
    var id: Long = 0,
    val name: String,
    val medicament: Medicament,
    val parent: Ingredient? = null,
    val mutuallyExclusive: List<Ingredient> = mutableListOf(),
    val prohibitingCountries: List<Country> = mutableListOf()
)