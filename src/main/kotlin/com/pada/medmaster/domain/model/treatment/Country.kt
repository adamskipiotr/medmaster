package com.pada.medmaster.domain.model.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IngredientEntity

class Country(
    var id: Long = 0,
    val name: String,
    val prohibitedIngredients: Set<Ingredient>? = HashSet()
) {

}
