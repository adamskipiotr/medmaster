package com.pada.medmaster.domain.service.patient.stubs

import com.pada.medmaster.application.ports.out.patient.GetPatientPort
import com.pada.medmaster.domain.model.patient.Intake
import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.domain.model.patient.PatientAddress
import com.pada.medmaster.domain.model.patient.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeFrequency
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month

class GetPatientPortStub() : GetPatientPort {

    private val intake = createStubIntake()

    private val treatment = createStubTreatment()

    private val patientAddress = createStubPatientAddress()

    private val secondTreatment = createStubTreatment(2L)

    private val secondAddress = createStubPatientAddress(2L, "OtherVoivodeship")

    private val patients = mutableMapOf(
        createStubPatient(1L, treatment, patientAddress),
        createStubPatient(2L, secondTreatment, secondAddress)
    )

    override fun get(id: Long): Patient {
        return patients[id] ?: throw NoSuchElementException("Patient not found")
    }

    private fun createStubPatient(id: Long, treatment: Treatment, patientAddress: PatientAddress) = (
            id to Patient(
                id, "Name", "Lastname", LocalDate.of(1999, Month.DECEMBER, 20), mutableListOf(),
                Gender.XX, mutableListOf(), mutableListOf(treatment), patientAddress
            )
            )

    private fun createStubPatientAddress(id: Long = 1L, voivodeship: String = "Voivodeship") = PatientAddress(
        id, "Country", voivodeship, "District", "Community", "Location",
        "Street", "10A", "5B", "40200"
    )

    private fun createStubTreatment(id: Long = 1L) = Treatment(
        id, "Disease", "Description", "Code", mutableListOf(), null, mutableListOf(intake),
        LocalDateTime.of(2025, Month.FEBRUARY, 2, 12, 30), LocalDateTime.of(2025, Month.MAY, 4, 12, 50)
    )

    private fun createStubIntake() = Intake(
        1L, 3L, IntakeForm.SHOT, 3, IntakeFrequency.ONCE_A_DAY, mutableListOf(), 4, null
    )

}
