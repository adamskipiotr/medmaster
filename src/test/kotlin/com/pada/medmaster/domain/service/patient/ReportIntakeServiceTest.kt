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
    fun shouldReportNewIntakeDateWhenValidationPasses() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest()

        //when
        sut.report(patientId = 1L, treatmentId = 1L,  intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates.map { intakeDate -> intakeDate.date }
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,1,10,0,0),  intakeDates.last())
    }

    @Test
    fun shouldThrowExceptionWhenOverdoseDetected() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest()

        //when
        sut.report(patientId = 1L, treatmentId = 1L,  intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates.map { intakeDate -> intakeDate.date }
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,1,10,0,0),  intakeDates.last())
    }

    @Test
    fun shouldThrowExceptionWhenDailyIntakeTooEarly() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest()

        //when
        sut.report(patientId = 1L, treatmentId = 1L,  intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates.map { intakeDate -> intakeDate.date }
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,1,10,0,0),  intakeDates.last())
    }

    @Test
    fun shouldThrowExceptionWhenDailyIntakeTooLate() {
        //given
        val testPatient = getPatientPort.get(1L)
        val reportIntakeRequest = createIntakeRequest()

        //when
        sut.report(patientId = 1L, treatmentId = 1L,  intakeId = 1L, reportIntakeRequest)

        //then
        val intakeDates = testPatient.treatments.first().intakes.first().intakeDates.map { intakeDate -> intakeDate.date }
        Assertions.assertEquals(2, intakeDates.size)
        Assertions.assertEquals(LocalDateTime.of(2025,Month.MARCH,1,10,0,0),  intakeDates.last())
    }

    private fun createIntakeRequest(medicamentId: Long = 1L) = ReportIntakeRequest(
            medicamentId, IntakeForm.SHOT,5, LocalDateTime.of(2025,Month.MARCH,2,10,0,0)
    )
}