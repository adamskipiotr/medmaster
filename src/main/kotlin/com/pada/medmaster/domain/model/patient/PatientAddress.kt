package com.pada.medmaster.domain.model.patient

open class PatientAddress(
    val id: Long? = null,
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
        require(voivodeship.isNotEmpty()) { "A pharmacy must have its voivodeship provided." }
        require(district.isNotEmpty()) { "A pharmacy must have its district provided." }
        require(community.isNotEmpty()) { "A pharmacy must have its community provided." }
        require(location.isNotEmpty()) { "A pharmacy must have its location provided." }
        require(buildingNumber.isNotEmpty()) { "A pharmacy must have its building number provided." }
        require(zipCode.isNotEmpty()) { "A pharmacy must have its zip code provided." }
    }
}