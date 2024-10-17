package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import java.time.LocalDateTime

class Intake(
    private val medicament: Medicament,
    private val form: IntakeForm,
    private val dosage: Int,
    private val intakeFrequency: IntakeFrequency,
    private val intakeDates: List<LocalDateTime>,
    private val intakeLimit: Int
){ // learn: internal in Kotlin vs package-private in Java
}