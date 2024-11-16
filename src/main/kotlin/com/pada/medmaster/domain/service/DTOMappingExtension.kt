package com.pada.medmaster.domain.service

import com.pada.medmaster.application.dto.request.medicament.IngredientRequestDTO
import com.pada.medmaster.application.dto.request.medicament.MedicamentRequestDTO
import com.pada.medmaster.application.dto.request.medicament.PharmacyDTO
import com.pada.medmaster.application.dto.request.treatment.*
import com.pada.medmaster.domain.model.medicament.Ingredient
import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.domain.model.pharmacy.Pharmacy
import com.pada.medmaster.domain.model.treatment.*

// Extension functions for mapping each part
fun TreatmentRequestDTO.toDomain(): Treatment {
    val treatment = Treatment(
        id = null,
        disease = disease,
        description = description,
        code = code,
        beginDate = beginDate,
        endDate = endDate
    )
    val medicalProcedures = this.medicalProcedures.map { it.toDomain() }
    treatment.addMedicalProcedures(medicalProcedures)
    val intakes = this.intakes.map { it.toDomain() }
    treatment.addIntakes(intakes)
    return treatment
}

fun MedicalProcedureRequestDTO.toDomain(): MedicalProcedure = MedicalProcedure(
    id = null,
    name = name,
    description = description,
    procedureDate = procedureDate,
    minimalRecoveryDate = minimalRecoveryDate,
    treatment = null
)

fun IntakeRequestDTO.toDomain(): Intake {
    val intake = Intake(
        id = null,
        medicamentId = medicamentId,
        form = form,
        dosage = dosage,
        intakeFrequency = intakeFrequency,
        treatment = null,
        intakeLimit = intakeLimit,
    )
    val intakeDates = this.intakeDates.map { it.toDomain() }
    intake.addIntakeDates(intakeDates)
    return intake
}

fun IntakeDateRequestDTO.toDomain(): IntakeDate = IntakeDate(
    id = null,
    date = date,
    intake = null
)

fun MedicamentRequestDTO.toDomain(): Medicament {
    val ingredients = this.ingredients.map { it.toDomain() }.toMutableList()
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

fun PharmacyDTO.toDomain(): Pharmacy {

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


fun IngredientRequestDTO.toDomain(): Ingredient {
    val ingredient = Ingredient(
        name = name,
        medicament = null,
        parent = null,
        mutuallyExclusive = mutableListOf(),
        prohibitingCountries = prohibitingCountries.map { it.toDomain() }
    )
    this.mutuallyExclusive?.forEach { childDTO -> ingredient.addMutuallyExclusiveIngredient(childDTO.toDomain()) }
    return ingredient
}

fun CountryRequestDTO.toDomain(): Country = Country(
    id = null,
    name = name
)
