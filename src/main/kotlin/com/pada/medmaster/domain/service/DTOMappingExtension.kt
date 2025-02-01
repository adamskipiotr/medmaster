package com.pada.medmaster.domain.service

import com.pada.medmaster.application.dto.request.ingredient.CreateIngredientRequest
import com.pada.medmaster.application.dto.request.medicament.CreateMedicamentRequest
import com.pada.medmaster.application.dto.request.medicament.CreatePharmacyRequest
import com.pada.medmaster.application.dto.request.patient.CreatePatientRequest
import com.pada.medmaster.application.dto.request.treatment.*
import com.pada.medmaster.domain.model.ingredient.Country
import com.pada.medmaster.domain.model.ingredient.Ingredient
import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.domain.model.patient.*
import com.pada.medmaster.domain.model.pharmacy.Pharmacy

// Extension functions for mapping each part
fun CreateTreatmentRequest.toDomain(): Treatment {
    val treatment = Treatment(
        id = null,
        disease = disease,
        description = description,
        code = code,
        beginDate = beginDate,
        endDate = endDate,
        patient = null
    )
    val medicalProcedures = this.medicalProcedures.map { it.toDomain() }
    treatment.addMedicalProcedures(medicalProcedures)
    val intakes = this.intakes.map { it.toDomain() }
    treatment.addIntakes(intakes)
    return treatment
}

fun CreateMedicalProcedureRequest.toDomain(): MedicalProcedure = MedicalProcedure(
    id = null,
    name = name,
    description = description,
    procedureDate = procedureDate,
    minimalRecoveryDate = minimalRecoveryDate,
    treatment = null
)

fun CreateIntakeRequest.toDomain(): Intake {
    val intake = Intake(
        id = null,
        medicamentId = medicamentId,
        form = form,
        dosage = dosage,
        intakeFrequency = intakeFrequency,
        treatment = null,
        intakeLimit = intakeLimit,
    )
    return intake
}

fun IntakeDateRequest.toDomain(): IntakeDate = IntakeDate(
    id = null,
    date = date,
    intake = null
)

fun CreateMedicamentRequest.toDomain(): Medicament {
    val pharmacies = this.pharmacies.map { it.toDomain() }.toMutableList()
    val medicament = Medicament(
        id = null,
        name = name,
        producer = producer,
        overdoseCounteractions = overdoseCounteraction,
        ingredients = ingredients,
        pharmacies = pharmacies
    )
    return medicament
}

fun CreatePharmacyRequest.toDomain(): Pharmacy {
    val pharmacy = Pharmacy(
        name = name,
        voivodeship = voivodeship,
        district = district,
        community = community,
        location = location,
        street = street,
        buildingNumber = buildingNumber,
        apartmentNumber = apartmentNumber,
        zipCode = zipCode
    )
    return pharmacy
}

fun CreateIngredientRequest.toDomain(): Ingredient {
    val ingredient = Ingredient(
        name = name,
        medicament = null,  // This will be set later if necessary
        prohibitingCountries = mutableListOf()
    )
    val prohibitingCountries = prohibitingCountries?.map { it.toDomain() }.orEmpty()
    ingredient.addProhibitingCountries(prohibitingCountries)
    return ingredient
}

fun CountryRequest.toDomain(): Country = Country(
    id = null,
    name = name
)

fun CreatePatientRequest.toDomain(): Patient {
    val patient = Patient(
        name = name,
        lastName = lastName,
        birthDate = birthDate,
        specialHealthConditions = specialHealthConditions,
        gender = gender,
        allergicIngredients = allergicIngredients

    )
    return patient
}
