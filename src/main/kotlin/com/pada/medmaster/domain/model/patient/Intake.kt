package com.pada.medmaster.domain.model.patient

import com.pada.medmaster.application.dto.request.patient.ReportIntakeRequest
import com.pada.medmaster.domain.events.MissedIntakeEvent
import java.time.Clock
import java.time.LocalDateTime

class Intake(
    val id: Long? = null,
    val medicamentId: Long?,
    val form: IntakeForm,
    val dosage: Int,
    val intakeFrequency: IntakeFrequency?,
    val intakeDates: MutableList<IntakeDate> = mutableListOf(),  // Initialize the list
    val intakeLimit: Int,
    var treatment: Treatment?,
) {

    fun reportIntake(reportIntakeRequest: ReportIntakeRequest) { // refactor - can ReportIntakeRequest be ued inside domain?
        val intakeDate = createIntakeDate(reportIntakeRequest);
        intakeDate?.let { intakeDates.add(intakeDate) }
    }

    private fun createIntakeDate(reportIntakeRequest: ReportIntakeRequest): IntakeDate? {
        val overdose = reportIntakeRequest.dosage > intakeLimit
        val newIntakeDate = reportIntakeRequest.date

        val lastIntake = intakeDates.maxOfOrNull { it.date } ?: return IntakeDate(
            null, newIntakeDate,
            newIntakeDate, newIntakeDate, true, overdose, null
        )

        val (minGap, maxGap) = getIntakeTimeGap(lastIntake) ?: return null

        val inTimeGap = newIntakeDate in minGap..maxGap
        return IntakeDate(null, newIntakeDate, minGap, maxGap, inTimeGap, overdose, this)
    }

    fun validateIntakeRegularity(clock: Clock): MissedIntakeEvent? {
        val lastIntake = intakeDates.last().date

        val (_, maxGap) = getIntakeTimeGap (lastIntake) ?: return null
        val currentDate = LocalDateTime.now(clock)
        if (currentDate.isAfter(maxGap)) {
            return MissedIntakeEvent(treatment!!.patient!!.id!!, treatment!!.id!!, id!!, maxGap)
        }
        return null
    }

    private fun getIntakeTimeGap(lastIntake: LocalDateTime): Pair<LocalDateTime, LocalDateTime>? {
        return when (intakeFrequency) {
            IntakeFrequency.HOURLY -> lastIntake.plusMinutes(50) to lastIntake.plusMinutes(70)
            IntakeFrequency.TWICE_A_DAY -> lastIntake.plusHours(11) to lastIntake.plusHours(13)
            IntakeFrequency.THREE_TIMES_A_DAY -> lastIntake.plusHours(5) to lastIntake.plusHours(7)
            IntakeFrequency.ONCE_A_DAY -> lastIntake.plusDays(1).minusHours(3) to lastIntake.plusDays(1).plusHours(3)
            null -> null
        }
    }
}
