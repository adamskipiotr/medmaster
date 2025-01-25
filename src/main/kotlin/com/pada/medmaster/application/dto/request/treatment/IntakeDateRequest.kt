package com.pada.medmaster.application.dto.request.treatment

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime


//Note: @JsonProperty is required for a single field DTO to avoid: "No Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator"
// To learn: Why?
data class IntakeDateRequest(
    @JsonProperty("date")
    val date: LocalDateTime,
)