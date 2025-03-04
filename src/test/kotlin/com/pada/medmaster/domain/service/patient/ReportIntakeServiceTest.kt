package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.patient.ReportIntakeRequest
import com.pada.medmaster.domain.model.patient.Intake
import com.pada.medmaster.domain.model.patient.IntakeFrequency
import com.pada.medmaster.domain.service.patient.stubs.GetPatientPortStub
import com.pada.medmaster.domain.service.patient.stubs.UpdatePatientPortStub
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

internal class ReportIntakeServiceTest {

    private val getPatientPort = GetPatientPortStub()
    private val updatePatientPort = UpdatePatientPortStub()
    private val sut = ReportIntakeService(getPatientPort, updatePatientPort)


    @Test
    fun shouldReportNewIntakeInTimeGapAndNotOverdosedDateWhenValidationPasses() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest()

        //when
        sut.report(patientId = 1L, treatmentId = 1L, intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 2, 10, 0), intakeDates.last().date)
        Assertions.assertEquals(true, intakeDates.last().intakeInTimeGap)
        Assertions.assertEquals(false, intakeDates.last().overdose)

    }

    @Test
    fun shouldReportOverdosedIntakeInTimeGapWhenSingleDoseExceedsIntakeLimit() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest().copy(dosage = 10)

        //when
        sut.report(patientId = 1L, treatmentId = 1L, intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(true, intakeDates.last().overdose)
    }

    @Test
    fun shouldReportIntakeNotInTimeGapWhenDailyIntakeTooLate() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest().copy(
            date = LocalDateTime.of(
                2025,
                Month.MARCH,
                3,
                15,
                0
            )
        )  //To learn: this is the best way to modify single value
        //don't try implementing any Builders unless it is really necessary
        //when
        sut.report(patientId = 1L, treatmentId = 1L, intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 2, 7, 0), intakeDates.last().expectedDateMinGap)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 2, 13, 0), intakeDates.last().expectedDateMaxGap)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 3, 15, 0), intakeDates.last().date)
        Assertions.assertEquals(false, intakeDates.last().intakeInTimeGap)
    }

    @Test
    fun shouldReportIntakeNotInTimeGapWhenDailyIntakeTooEarly() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest().copy(
            date = LocalDateTime.of(
                2025,
                Month.MARCH,
                1,
                23,
                59
            )
        )  //To learn: this is the best way to modify single value
        //don't try implementing any Builders unless it is really necessary
        //when
        sut.report(patientId = 1L, treatmentId = 1L, intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 2, 7, 0), intakeDates.last().expectedDateMinGap)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 2, 13, 0), intakeDates.last().expectedDateMaxGap)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 1, 23, 59), intakeDates.last().date)
        Assertions.assertEquals(false, intakeDates.last().intakeInTimeGap)
    }

    @Test
    fun shouldReportIntakeNotInTimeGapWhenHourlyIntakeTooEarly() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest().copy(
            date = LocalDateTime.of(
                2025,
                Month.MARCH,
                1,
                11,
                30
            )
        )

        val originalIntake = testPatient.treatments.first().intakes.first()
        val modifiedIntake = Intake(
            originalIntake.id,
            originalIntake.medicamentId,
            originalIntake.form,
            originalIntake.dosage,
            IntakeFrequency.HOURLY,
            originalIntake.intakeDates,
            originalIntake.intakeLimit,
            originalIntake.treatment
        )

        testPatient.treatments.first().intakes[0] = modifiedIntake

        //when
        sut.report(patientId = 1L, treatmentId = 1L, intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 1, 10, 50), intakeDates.last().expectedDateMinGap)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 1, 11, 10), intakeDates.last().expectedDateMaxGap)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 1, 11, 30), intakeDates.last().date)
        Assertions.assertEquals(false, intakeDates.last().intakeInTimeGap)
    }

    @Test
    fun shouldReportIntakeNotInTimeGapWhenTwiceADayIntakeTooEarly() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest().copy(
            date = LocalDateTime.of(
                2025,
                Month.MARCH,
                1,
                20,
                59
            )
        )

        val originalIntake = testPatient.treatments.first().intakes.first()
        val modifiedIntake = Intake(
            originalIntake.id,
            originalIntake.medicamentId,
            originalIntake.form,
            originalIntake.dosage,
            IntakeFrequency.TWICE_A_DAY,
            originalIntake.intakeDates,
            originalIntake.intakeLimit,
            originalIntake.treatment
        )

        testPatient.treatments.first().intakes[0] = modifiedIntake

        //when
        sut.report(patientId = 1L, treatmentId = 1L, intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 1, 21, 0), intakeDates.last().expectedDateMinGap)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 1, 23,0), intakeDates.last().expectedDateMaxGap)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 1, 20, 59), intakeDates.last().date)
        Assertions.assertEquals(false, intakeDates.last().intakeInTimeGap)
    }

    @Test
    fun shouldReportIntakeNotInTimeGapWhenThreeTimesADayIntakeTooEarly() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest().copy(
            date = LocalDateTime.of(
                2025,
                Month.MARCH,
                1,
                14,
                45
            )
        )

        val originalIntake = testPatient.treatments.first().intakes.first()
        val modifiedIntake = Intake(
            originalIntake.id,
            originalIntake.medicamentId,
            originalIntake.form,
            originalIntake.dosage,
            IntakeFrequency.THREE_TIMES_A_DAY,
            originalIntake.intakeDates,
            originalIntake.intakeLimit,
            originalIntake.treatment
        )

        testPatient.treatments.first().intakes[0] = modifiedIntake

        //when
        sut.report(patientId = 1L, treatmentId = 1L, intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 1, 15, 0), intakeDates.last().expectedDateMinGap)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 1, 17, 0), intakeDates.last().expectedDateMaxGap)
        Assertions.assertEquals(LocalDateTime.of(2025, Month.MARCH, 1, 14, 45), intakeDates.last().date)
        Assertions.assertEquals(false, intakeDates.last().intakeInTimeGap)
    }


    private fun createIntakeRequest(medicamentId: Long = 1L) = ReportIntakeRequest(
        medicamentId, IntakeForm.SHOT, 1, LocalDateTime.of(2025, Month.MARCH, 2, 10, 0, 0)
    )
}