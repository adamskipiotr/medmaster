package com.pada.medmaster.domain.model.patient

import com.pada.medmaster.application.dto.request.patient.ReportIntakeRequest

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
        val intakeDate = createIntakeDate(reportIntakeRequest);
        intakeDate?.let { intakeDates.add(intakeDate) }
    }

    private fun createIntakeDate(reportIntakeRequest: ReportIntakeRequest): IntakeDate? {
        val overdose = reportIntakeRequest.dosage > intakeLimit
        val newIntakeDate = reportIntakeRequest.date

        val lastIntake = intakeDates.maxOfOrNull { it.date } ?: return IntakeDate(null, newIntakeDate,
            newIntakeDate, newIntakeDate, true, overdose, null)
        
        val (minGap, maxGap) = when (intakeFrequency) {
            IntakeFrequency.HOURLY -> lastIntake.plusMinutes(50) to lastIntake.plusMinutes(70)
            IntakeFrequency.TWICE_A_DAY -> lastIntake.plusHours(11) to lastIntake.plusHours(13)
            IntakeFrequency.THREE_TIMES_A_DAY -> lastIntake.plusHours(5) to lastIntake.plusHours(7)
            IntakeFrequency.ONCE_A_DAY -> lastIntake.plusDays(1).minusHours(3) to lastIntake.plusDays(1).plusHours(3)
            null -> return null
        }

        val inTimeGap = newIntakeDate in minGap..maxGap
        return IntakeDate(null, newIntakeDate, minGap, maxGap, inTimeGap, overdose, this)
    }
}
