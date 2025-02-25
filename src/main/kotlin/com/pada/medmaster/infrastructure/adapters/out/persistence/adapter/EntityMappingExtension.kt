package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter

import com.pada.medmaster.domain.model.ingredient.Country
import com.pada.medmaster.domain.model.ingredient.Ingredient
import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.domain.model.medicament.Pharmacy
import com.pada.medmaster.domain.model.medicament.PharmacyAddress
import com.pada.medmaster.domain.model.patient.*
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.ingredient.CountryEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.ingredient.IngredientEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.MedicamentEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.PharmacyAddressEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.PharmacyEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.*

// Extension functions for mapping each part

fun of(medicalProcedure: MedicalProcedure, treatmentEntity: TreatmentEntity) = MedicalProcedureEntity().apply {
    name = medicalProcedure.name
    description = medicalProcedure.description
    procedureDate = medicalProcedure.procedureDate
    minimalRecoveryDate = medicalProcedure.minimalRecoveryDate
    this.treatment = treatmentEntity
}

fun of(medicalProcedure: MedicalProcedure) = MedicalProcedureEntity().apply {
    name = medicalProcedure.name
    description = medicalProcedure.description
    procedureDate = medicalProcedure.procedureDate
    minimalRecoveryDate = medicalProcedure.minimalRecoveryDate
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
    ingredientsIds.addAll(medicament.ingredients)
}

fun of(pharmacy: Pharmacy) = PharmacyEntity().apply {
    name = pharmacy.name
    address = pharmacy.address?.let { of(it) }

}

fun of(pharmacyAddress: PharmacyAddress) = PharmacyAddressEntity().apply {
    voivodeship = pharmacyAddress.voivodeship
    district = pharmacyAddress.district
    community = pharmacyAddress.community
    location = pharmacyAddress.location
    street = pharmacyAddress.street
    buildingNumber = pharmacyAddress.buildingNumber
    apartmentNumber = pharmacyAddress.apartmentNumber
    zipCode = pharmacyAddress.zipCode
}

fun of(ingredient: Ingredient): IngredientEntity {
    return IngredientEntity().apply {
        id = ingredient.id ?: 0
        name = ingredient.name
        incompatibleIngredients.addAll(ingredient.incompatibleIngredients?.map { i -> of(i) }.orEmpty())
        prohibitingCountries.addAll(ingredient.prohibitingCountries.map { i -> of(i) })
    }
}

fun of(country: Country) = CountryEntity().apply {
    name = country.name
}

fun of(patient: Patient) = PatientEntity().apply {
    id = patient.id ?: 0
    name = patient.name
    lastName = patient.lastName
    birthDate = patient.birthDate
    gender = patient.gender
    specialHealthConditions = patient.specialHealthConditions
    allergicIngredients.addAll(patient.allergicIngredients)
    address = patient.address?.let { of(it) }
    treatments.addAll(patient.treatments.map { i -> of(i, this) }) // Pass the PatientEntity
}

fun of(patientAddress: PatientAddress) = PatientAddressEntity().apply {
    country = patientAddress.country
    voivodeship = patientAddress.voivodeship
    district = patientAddress.district
    community = patientAddress.community
    location = patientAddress.location
    street = patientAddress.street
    buildingNumber = patientAddress.buildingNumber
    apartmentNumber = patientAddress.apartmentNumber
    zipCode = patientAddress.zipCode
}

fun of(treatment: Treatment, patientEntity: PatientEntity) = TreatmentEntity().apply {
    id = treatment.id ?: 0
    disease = treatment.disease
    description = treatment.description
    code = treatment.code
    beginDate = treatment.beginDate
    endDate = treatment.endDate
    medicalProcedures.addAll(treatment.medicalProcedures.map { p -> of(p, this) })
    intakes.addAll(treatment.intakes.map { i -> of(i, this) })
    this.patient = patientEntity // Set the PatientEntity
}

fun of(treatment: Treatment) = TreatmentEntity().apply {
    id = treatment.id ?: 0
    disease = treatment.disease
    description = treatment.description
    code = treatment.code
    beginDate = treatment.beginDate
    endDate = treatment.endDate
    medicalProcedures.addAll(treatment.medicalProcedures.map { p -> of(p) })
    intakes.addAll(treatment.intakes.map { i -> of(i) })
}

fun of(intake: Intake, treatmentEntity: TreatmentEntity) = IntakeEntity().apply {
    id = intake.id ?: 0
    form = intake.form
    dosage = intake.dosage
    intakeFrequency = intake.intakeFrequency!!
    intakeLimit = intake.intakeLimit
    medicamentId = intake.medicamentId
    intakeDates.addAll(intake.intakeDates.map { of(it) })
    this.treatment = treatmentEntity
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