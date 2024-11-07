package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter

import com.pada.medmaster.application.dto.request.*
import com.pada.medmaster.domain.model.treatment.*
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.*

// Extension functions for mapping each part
fun of(treatment: Treatment) = TreatmentEntity().apply {
    disease = treatment.disease
    description = treatment.description
    code = treatment.code
    beginDate = treatment.beginDate
    endDate = treatment.endDate
    medicalProcedures.addAll(treatment.medicalProcedures.map { p -> of(p) })
    intakes.addAll(treatment.intakes.map { i -> of(i) })
}

fun of(medicalProcedure: MedicalProcedure) = MedicalProcedureEntity().apply {
        name = medicalProcedure.name
        description = medicalProcedure.description
        procedureDate = medicalProcedure.procedureDate
        minimalRecoveryDate = medicalProcedure.minimalRecoveryDate
    }

fun of(intake: Intake) = IntakeEntity().apply {
    id = intake.id ?: 0
    form = intake.form
    dosage = intake.dosage
    intakeFrequency = intake.intakeFrequency!!
    intakeLimit = intake.intakeLimit
    medicament = of(intake.medicament!!)
    intakeDates.addAll(intake.intakeDates.map { of(it) })
}

fun of(intakeDate: IntakeDate): IntakeDateEntity {
    return IntakeDateEntity(
        intakeDate.id ?: 0,
        intakeDate.date
    )
}

fun of(medicament: Medicament) = MedicamentEntity().apply {
    name = medicament.name
    ingredients.addAll(medicament.ingredients.map { i -> of(i) })
}


fun of(ingredient: Ingredient) = IngredientEntity().apply {
        name = ingredient.name
        prohibitingCountries.addAll(ingredient.prohibitingCountries.map { of(it) })
}

fun of(country: Country) = CountryEntity().apply {
        name = country.name
}
