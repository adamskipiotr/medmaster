package com.pada.medmaster.domain.service

import com.pada.medmaster.application.dto.request.*
import com.pada.medmaster.domain.model.treatment.*

// Extension functions for mapping each part
fun TreatmentRequestDTO.toDomain(): Treatment {
   val treatment =  Treatment(
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

fun IntakeRequestDTO.toDomain(): Intake = Intake(
    id = null,
    medicament = medicament.toDomain(),
    form = form,
    dosage = dosage,
    intakeFrequency = intakeFrequency,
    treatment = null,
    intakeLimit = intakeLimit,
)

fun MedicamentRequestDTO.toDomain(): Medicament {
    val medicament = Medicament(
        id = null,
        name = name,
    )
    val ingredients = this.ingredients.map { it.toDomain() }.toMutableList()
    medicament.addIngredients(ingredients)
    return medicament
}


fun IngredientRequestDTO.toDomain(): Ingredient {
    val ingredient = Ingredient(
        name = name,
        medicament = null,
        parent = null,
        mutuallyExclusive = mutableListOf(),
        prohibitingCountries = prohibitingCountries?.map { it.toDomain() }
    )
    this.mutuallyExclusive?.forEach { childDTO -> ingredient.addMutuallyExclusiveIngredient(childDTO.toDomain()) }
    return ingredient
}

fun CountryRequestDTO.toDomain(): Country = Country(
    id = null,
    name = name
)
