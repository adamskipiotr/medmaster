package com.pada.medmaster.domain.model.patient

import com.pada.medmaster.application.dto.request.patient.ReportIntakeRequest
import com.pada.medmaster.domain.exception.MedicalProcedureScheduledOnExpectedRecoveryTimeException
import com.pada.medmaster.domain.exception.MedicalProcedureScheduledOnRecoveryTimeException
import com.pada.medmaster.domain.exception.MedicalProcedureScheduledOnTheSameDayException
import java.time.LocalDateTime

class Treatment(
    val id: Long? = null,
    val disease: String,
    val description: String,
    val code: String,
    val medicalProcedures: MutableList<MedicalProcedure> = mutableListOf(),
    var patient: Patient?,  // fix to val?
    val intakes: MutableList<Intake> = mutableListOf(),
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
) {

    fun addMedicalProcedure(medicalProcedure: MedicalProcedure) {
        val procedureDate = medicalProcedure.procedureDate.toLocalDate()
        val recoveryDate = medicalProcedure.minimalRecoveryDate.toLocalDate()

        when {
            medicalProcedures.any { it.procedureDate.toLocalDate() == procedureDate } ->
                throw MedicalProcedureScheduledOnTheSameDayException("Medical procedure can't be scheduled on $procedureDate: Other medical procedure is already scheduled for the date")

            medicalProcedures.any {
                val existingDate = it.procedureDate.toLocalDate()
                existingDate.isAfter(procedureDate) && existingDate.isBefore(recoveryDate)
            } ->
                throw MedicalProcedureScheduledOnExpectedRecoveryTimeException("Medical procedure can't be scheduled on $procedureDate: Other medical procedure is scheduled during the recovery time of the new medical procedure")

            medicalProcedures.any {
                val existingProcedureDate = it.procedureDate.toLocalDate()
                val existingRecoveryDate = it.minimalRecoveryDate.toLocalDate()
                procedureDate.isAfter(existingProcedureDate) && procedureDate.isBefore(existingRecoveryDate)
            } ->
                throw MedicalProcedureScheduledOnRecoveryTimeException("Medical procedure can't be scheduled on $procedureDate: Patient will be in recovery after a previously scheduled procedure")
        }
        medicalProcedures.add(medicalProcedure)
    }


    fun addMedicalProcedures(medicalProcedures: List<MedicalProcedure>) {
        this.medicalProcedures.addAll(medicalProcedures)
    }

    fun addIntakes(intakes: List<Intake>) {
        this.intakes.addAll(intakes)
    }

    fun addIntake(intake: Intake) {
        intakes.add(intake)
        intake.treatment = this
    }

    fun reportIntake(intakeId: Long, reportIntakeRequest: ReportIntakeRequest) {
        val intake = getIntake(intakeId)
        intake.reportIntake(reportIntakeRequest)
    }

    private fun getIntake(intakeId: Long) = (intakes.find { intake -> intake.id!! == intakeId }
        ?: throw RuntimeException("Intake with given Id not found"))

}
