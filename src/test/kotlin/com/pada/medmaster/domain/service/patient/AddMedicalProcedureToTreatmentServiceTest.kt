package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.patient.CreateMedicalProcedureRequest
import com.pada.medmaster.domain.exception.MedicalProcedureScheduledOnExpectedRecoveryTimeException
import com.pada.medmaster.domain.exception.MedicalProcedureScheduledOnRecoveryTimeException
import com.pada.medmaster.domain.exception.MedicalProcedureScheduledOnTheSameDayException
import com.pada.medmaster.domain.service.patient.stubs.GetPatientPortStub
import com.pada.medmaster.domain.service.patient.stubs.UpdatePatientPortStub
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.*

class AddMedicalProcedureToTreatmentServiceTest {

    private val fixedClock = Clock.fixed(
        Instant.parse("2025-01-01T11:45:00Z"), // Fixed "now" in tests
        ZoneId.systemDefault()
    )

    private val getPatientPort = GetPatientPortStub()
    private val updatePatientPort = UpdatePatientPortStub()
    private val sut = AddMedicalProcedureToTreatmentService(getPatientPort, updatePatientPort, fixedClock)

    @Test
    fun shouldAddMedicalProcedureToTreatmentWhenValidationPasses() {
        //given
        val testPatient = getPatientPort.get(1L)
        val createMedicalProcedureRequest =
            createMedicalProcedureRequest(
                LocalDateTime.of(2026, Month.JANUARY, 10, 11, 45),
                LocalDateTime.of(2026, Month.FEBRUARY, 10, 11, 45)
            )

        //when
        sut.add(patientId = 1L, treatmentId = 1L, createMedicalProcedureRequest)

        //then
        assertEquals(3, testPatient.treatments[0].medicalProcedures.size)
    }

    @Test
    fun shouldThrowMedicalProcedureScheduledOnTheSameDayExceptionWhenPatientHasOtherMedicalProcedureScheduledOnThisDay() {
        //given
        val createMedicalProcedureRequest =
            createMedicalProcedureRequest(
                LocalDateTime.of(2025, Month.APRIL, 10, 11, 45),
                LocalDateTime.of(2025, Month.APRIL, 11, 11, 45)
            )

        //when
        val exception = assertThrows<MedicalProcedureScheduledOnTheSameDayException> {
            sut.add(patientId = 2L, treatmentId = 2L, createMedicalProcedureRequest)
        }

        //then
        assertEquals(
            "Medical procedure can't be scheduled on 2025-04-10: Other medical procedure is already scheduled for the date",
            exception.message
        )
    }


    @Test
    fun shouldThrowMedicalProcedureScheduledOnExpectedRecoveryTimeExceptionWhenPatientHasOtherMedicalProcedureScheduledInRecoverTime() {
        //given

        val createMedicalProcedureRequest =
            createMedicalProcedureRequest(
                LocalDateTime.of(2025, Month.MARCH, 10, 11, 45),
                LocalDateTime.of(2025, Month.APRIL, 2, 11, 45)
            )

        //when
        val exception = assertThrows<MedicalProcedureScheduledOnExpectedRecoveryTimeException> {
            sut.add(patientId = 1L, treatmentId = 1L, createMedicalProcedureRequest)
        }

        //then
        assertEquals(
            "Medical procedure can't be scheduled on 2025-03-10: Other medical procedure is scheduled during the recovery time of the new medical procedure",
            exception.message
        )
    }

    @Test
    fun shouldThrowMedicalProcedureScheduledOnRecoveryTimeExceptionWhenNewProcedureScheduledOnRecoveryTime() {
        //given
        val createMedicalProcedureRequest =
            createMedicalProcedureRequest(
                LocalDateTime.of(2025, Month.APRIL, 12, 11, 45),
                LocalDateTime.of(2025, Month.APRIL, 13, 11, 45)
            )

        //when
        val exception = assertThrows<MedicalProcedureScheduledOnRecoveryTimeException> {
            sut.add(patientId = 1L, treatmentId = 1L, createMedicalProcedureRequest)
        }

        //then
        assertEquals(
            "Medical procedure can't be scheduled on 2025-04-12: Patient will be in recovery after a previously scheduled procedure",
            exception.message
        )
    }

    private fun createMedicalProcedureRequest(procedureDate: LocalDateTime, recoveryDate: LocalDateTime) =
        CreateMedicalProcedureRequest(
            "Medical Procedure Name", "Description", procedureDate, recoveryDate
        )
}