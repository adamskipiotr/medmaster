package com.pada.medmaster.application.dto.request.medicament

import org.jetbrains.annotations.NotNull

class PharmacyDTO(
    val name: String,
    val voivodeship: String,
    val district: String,
    val community: String,
    val location: String,
    val street: String,
    val buildingNumber: String,
    val apartmentNumber: String,
    val zipCode: String,
    )