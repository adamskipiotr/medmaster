package com.pada.medmaster.domain.model.patient

import com.pada.medmaster.application.dto.request.treatment.ReportIntakeRequest
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeFrequency
import java.time.Duration


class Intake(
        val id: Long? = null,
        val medicamentId: Long?,
        val form: IntakeForm,
        val dosage: Int,
        val intakeFrequency: IntakeFrequency?,
        val intakeDates: MutableList<IntakeDate> = mutableListOf(),  // Initialize the list
        val intakeLimit: Int,
        var treatment: Treatment?
) {
    fun addIntakeDates(intakeDates: List<IntakeDate>) {
        this.intakeDates.addAll(intakeDates)
    }

    fun reportIntake(reportIntakeRequest: ReportIntakeRequest) {
        val intakeDate = IntakeDate(null, reportIntakeRequest.date, this)
        controlDosage(reportIntakeRequest);
        intakeDates.add(intakeDate)
    }

    private fun controlDosage(reportIntakeRequest: ReportIntakeRequest) {
        val lastIntake = intakeDates.maxOfOrNull { intake -> intake.date }
        val newIntakeDate = reportIntakeRequest.date
        val gapBetweenLastIntake = Duration.between(newIntakeDate, lastIntake)

        when (intakeFrequency) {
            IntakeFrequency.HOURLY -> {
                validateIntakeInTimeGap(IntakeControlCommand(70,50,"Twice a day", gapBetweenLastIntake.toMinutes()))
            }
            IntakeFrequency.TWICE_A_DAY -> {
                validateIntakeInTimeGap(IntakeControlCommand(13,11,"Twice a day", gapBetweenLastIntake.toHours()))
            }
            IntakeFrequency.THREE_TIMES_A_DAY -> {
                validateIntakeInTimeGap(IntakeControlCommand(7,5,"Three times a day", gapBetweenLastIntake.toHours()))
            }
            IntakeFrequency.ONCE_A_DAY -> {
                validateIntakeInTimeGap(IntakeControlCommand(1,1,"Daily", gapBetweenLastIntake.toDays()))
            }
            else -> return
        }

        if (reportIntakeRequest.dosage > intakeLimit) {
            throw RuntimeException("Single intake limit exceeded")
        }
    }
    private fun validateIntakeInTimeGap(intakeControlCommand: IntakeControlCommand){
        if (intakeControlCommand.gapBetweenLastIntake > intakeControlCommand.maxGap) {
            throw RuntimeException("${intakeControlCommand.intakePeriodDescription} intake missed")
        }
        else if (intakeControlCommand.gapBetweenLastIntake < intakeControlCommand.minGap) {
            throw RuntimeException("${intakeControlCommand.intakePeriodDescription} intake too early")
        }
    }
    private data class IntakeControlCommand(
            val maxGap: Int,
            val minGap: Int,
            val intakePeriodDescription: String,
            val gapBetweenLastIntake: Long
    )
}
