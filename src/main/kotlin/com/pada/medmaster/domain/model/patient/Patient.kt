package com.pada.medmaster.domain.model.patient

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.SpecialHealthConditions
import java.time.LocalDate

// About bidirectional relationship:
// look at Domain Driven Design of Eric Evans - Chapter 5 - avoiding bidirectional relationship if there is no such need
class Patient(
    val id: Long? = null,
    val name: String,
    var lastName: String,
    var birthDate: LocalDate,
    var specialHealthConditions: MutableList<SpecialHealthConditions> = mutableListOf(),
    var gender: Gender,
    var allergicIngredients: MutableList<Long> = mutableListOf(),
    var treatments: MutableList<Treatment> = mutableListOf(),
    var address: PatientAddress?
) {

    fun addIntakeToTreatment(treatmentId: Long, intake: Intake) {
        val treatment = getTreatment(treatmentId)
        treatment.addIntake(intake)
    }

    fun addMedicalProcedureToTreatment(treatmentId: Long, medicalProcedure: MedicalProcedure) {
        val treatment = getTreatment(treatmentId)

        treatment.addMedicalProcedure(medicalProcedure)
    }

    fun addTreatment(treatment: Treatment) {
        treatments.add(treatment)
        treatment.patient = this
    }

    fun getMedicamentsInTreatment(treatmentId: Long): List<Long?> {
        val treatment = getTreatment(treatmentId)
        return treatment.intakes.map { intake -> intake.medicamentId }
    }

    fun getVoivodeship(): String? {
        return address?.voivodeship
    }

    fun getCountry(): String? {
        return address?.country
    }

    private fun getTreatment(treatmentId: Long) = (treatments.find { treatment -> treatment.id!! == treatmentId }
        ?: throw RuntimeException("Treatment with given Id not found"))
}
