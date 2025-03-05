package com.pada.medmaster.application.dto.request.patient

import com.fasterxml.jackson.annotation.JsonProperty

data class CountryRequest(
    @JsonProperty("name")
    val name: String,
)
