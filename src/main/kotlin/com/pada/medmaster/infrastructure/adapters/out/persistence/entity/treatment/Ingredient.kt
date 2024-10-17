package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

class Ingredient(val name: String,
    val mutuallyExclusive : List<Ingredient>,
    val prohibitingCountries: List<Country>) {

}
