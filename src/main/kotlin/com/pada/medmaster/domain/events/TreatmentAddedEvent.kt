package com.pada.medmaster.domain.events

data class TreatmentAddedEvent (
    val patientId: Long,
    val treatmentId: Long
)