package com.pada.medmaster.application.dto.request.patient

class CreatePatientAddressRequest(
    val country: String,
    val voivodeship: String,
    val district: String,
    val community: String,
    val location: String,
    val street: String,
    val buildingNumber: String,
    val apartmentNumber: String,
    val zipCode: String,
)