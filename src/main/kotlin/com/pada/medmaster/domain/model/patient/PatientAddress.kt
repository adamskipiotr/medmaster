package com.pada.medmaster.domain.model.patient

open class PatientAddress(
    val id: Long? = null,
    val country: String,
    val voivodeship: String,
    val district: String,
    val community: String,
    val location: String,
    val street: String,
    val buildingNumber: String,
    val apartmentNumber: String,
    val zipCode: String
) {
    init {
        require(country.isNotEmpty()) { "A patient must have its country provided." }
        require(voivodeship.isNotEmpty()) { "A patient must have its voivodeship provided." }
        require(district.isNotEmpty()) { "A patient must have its district provided." }
        require(community.isNotEmpty()) { "A patient must have its community provided." }
        require(location.isNotEmpty()) { "A patient must have its location provided." }
        require(buildingNumber.isNotEmpty()) { "A patient must have its building number provided." }
        require(zipCode.isNotEmpty()) { "A patient must have its zip code provided." }
    }
}