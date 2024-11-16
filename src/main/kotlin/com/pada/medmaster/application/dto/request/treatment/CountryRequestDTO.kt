package com.pada.medmaster.application.dto.request.treatment

import com.fasterxml.jackson.annotation.JsonProperty

data class CountryRequestDTO(
    @JsonProperty("name")
    val name: String,
)
