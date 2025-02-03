package com.pada.medmaster.application.dto.request.patient

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.SpecialHealthConditions
import java.time.LocalDate

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