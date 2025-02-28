package com.pada.medmaster.domain.model.patient

import com.pada.medmaster.application.dto.request.treatment.ReportIntakeRequest
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeFrequency
import java.time.Duration
import java.time.LocalDateTime

class Intake(
        val id: Long? = null,
        val medicamentId: Long?, // todo - call to Medicament Aggregate Root by Id or Reference here?
        val form: IntakeForm, // move to medicament
        val dosage: Int, // change do String, provided by Medicament
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
                if (gapBetweenLastIntake.toMinutes() > 70) {
                    throw RuntimeException("Hourly intake missed")
                }
                else if (gapBetweenLastIntake.toHours() < 50) {
                    throw RuntimeException("Hourly intake too early")
                }
            }

            IntakeFrequency.TWICE_A_DAY -> {
                if (gapBetweenLastIntake.toHours() > 13) {
                    throw RuntimeException("Twice a day intake missed")
                }
                else if (gapBetweenLastIntake.toHours() < 11) {
                    throw RuntimeException("Twice a day intake too early")
                }
            }

            IntakeFrequency.THREE_TIMES_A_DAY -> {
                if (gapBetweenLastIntake.toHours() > 7) {
                    throw RuntimeException("Three times a day intake missed")
                }
                else if (gapBetweenLastIntake.toHours() < 5) {
                    throw RuntimeException("Three times a day intake too early")
                }
            }

            IntakeFrequency.ONCE_A_DAY -> {
                if (gapBetweenLastIntake.toDays() > 1) {
                    throw RuntimeException("Daily intake missed")
                }
            }

            else -> return
        }

        if (reportIntakeRequest.dosage > intakeLimit) {
            throw RuntimeException("Single intake limit exceeded")
        }
    }
}
