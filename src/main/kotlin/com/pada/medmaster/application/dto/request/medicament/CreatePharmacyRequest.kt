package com.pada.medmaster.application.dto.request.medicament

class CreatePharmacyRequest(
    val name: String,
    val address: CreatePharmacyAddressRequest
)