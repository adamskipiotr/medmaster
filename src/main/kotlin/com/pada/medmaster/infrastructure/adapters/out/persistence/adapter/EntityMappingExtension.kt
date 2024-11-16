package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter

import com.pada.medmaster.domain.model.medicament.Ingredient
import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.domain.model.pharmacy.Pharmacy
import com.pada.medmaster.domain.model.treatment.*
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.CountryEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.ingredient.IngredientEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.MedicamentEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.PharmacyEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeDateEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.MedicalProcedureEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.TreatmentEntity

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
    medicamentId = intake.medicamentId
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
    producer = medicament.producer
    overdoseCounteractions = medicament.overdoseCounteractions
    pharmacies.addAll(medicament.pharmacies.map { i -> of(i) })
    ingredientsIds.addAll(medicament.ingredientsIds)
}

fun of(pharmacy: Pharmacy) = PharmacyEntity().apply {
    name = pharmacy.name
    voivodeship = pharmacy.voivodeship
    district = pharmacy.district
    community = pharmacy.community
    location = pharmacy.location
    street = pharmacy.street
    buildingNumber = pharmacy.buildingNumber
    apartmentNumber = pharmacy.apartmentNumber
    zipCode = pharmacy.zipCode
}

fun of(ingredient: Ingredient) = IngredientEntity().apply {
        name = ingredient.name
        prohibitingCountries.addAll(ingredient.prohibitingCountries.map { of(it) })
}

fun of(country: Country) = CountryEntity().apply {
        name = country.name
}
