package com.pada.medmaster.application.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

class CountryRequestDTO(
    @JsonProperty("name")
    val name: String,
) {

}
