package com.pada.medmaster.application.dto.request.treatment


data class TreatmentCodeDTO(
    val code: String,
    val maximalActiveTreatments: Int?
)