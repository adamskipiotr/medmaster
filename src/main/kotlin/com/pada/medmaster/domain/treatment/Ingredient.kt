package com.pada.medmaster.domain.treatment

class Ingredient(val name: String,
    val mutuallyExclusive : List<Ingredient>,
    val prohibitingCountries: List<Country>) {

}
