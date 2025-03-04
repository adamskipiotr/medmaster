package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.treatment.CreateIntakeRequest
import com.pada.medmaster.application.dto.request.treatment.ReportIntakeRequest
import com.pada.medmaster.domain.service.patient.stubs.*
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeFrequency
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
        sut.report(patientId = 1L, treatmentId = 1L,  intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,2,10,0,0),  intakeDates.last().date)
        Assertions.assertEquals(true,  intakeDates.last().intakeInTimeGap)
        Assertions.assertEquals(false,  intakeDates.last().overdose)

    }

    @Test
    fun shouldReportOverdosedIntakeInTimeGapWhenSingleDoseExceedsIntakeLimit() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest().copy(dosage = 10)

        //when
        sut.report(patientId = 1L, treatmentId = 1L,  intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(true,  intakeDates.last().overdose)
    }

    @Test
    fun shouldReportIntakeNotInTimeGapWhenDailyIntakeTooLate() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest().copy(date = LocalDateTime.of(2025, Month.MARCH, 3, 15, 0, 0))  //To learn: this is the best way to modify single value
                                                                                                                                                            //don't try implementing any Builders unless it is really necessary
        //when
        sut.report(patientId = 1L, treatmentId = 1L,  intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,2,7,0,0),  intakeDates.last().expectedDateMinGap)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,2,13,0,0),  intakeDates.last().expectedDateMaxGap)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,3,15,0,0),  intakeDates.last().date)
        Assertions.assertEquals(false,  intakeDates.last().intakeInTimeGap)
    }

    @Test
    fun shouldReportIntakeNotInTimeGapWhenDailyIntakeTooEarly() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest().copy(date = LocalDateTime.of(2025, Month.MARCH, 1, 23, 59, 0))  //To learn: this is the best way to modify single value
        //don't try implementing any Builders unless it is really necessary
        //when
        sut.report(patientId = 1L, treatmentId = 1L,  intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,2,7,0,0),  intakeDates.last().expectedDateMinGap)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,2,13,0,0),  intakeDates.last().expectedDateMaxGap)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,1,23,59,0),  intakeDates.last().date)
        Assertions.assertEquals(false,  intakeDates.last().intakeInTimeGap)
    }

    private fun createIntakeRequest(medicamentId: Long = 1L) = ReportIntakeRequest(
            medicamentId, IntakeForm.SHOT,1, LocalDateTime.of(2025,Month.MARCH,2,10,0,0)
    )
}