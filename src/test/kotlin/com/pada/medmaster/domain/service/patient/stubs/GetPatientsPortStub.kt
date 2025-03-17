package com.pada.medmaster.domain.service.patient.stubs

import com.pada.medmaster.application.ports.out.patient.GetPatientsPort
import com.pada.medmaster.domain.model.patient.*
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month

class GetPatientsPortStub : GetPatientsPort {

    private val patientAddress = createStubPatientAddress()

    private val firstTreatment = createStubTreatment(1L, mutableListOf())
    private val secondTreatment = createStubTreatment(2L, mutableListOf())
    private val thirdTreatment = createStubTreatment(3L, mutableListOf())

    private val firstPatient = createStubPatient(1L, patientAddress, mutableListOf(firstTreatment))
    private val secondPatient = createStubPatient(2L, patientAddress, mutableListOf(secondTreatment))
    private val thirdPatient = createStubPatient(3L, patientAddress, mutableListOf(thirdTreatment))

    private val firstTreatmentFirstIntake =
        createStubIntake(createIntakeDate(LocalDateTime.of(2025, Month.MARCH, 1, 12, 30)), firstTreatment)
    private val firstTreatmentSecondIntake =
        createStubIntake(createIntakeDate(LocalDateTime.of(2025, Month.MARCH, 2, 12, 30)), firstTreatment)
    private val firstTreatmentThirdIntake =
        createStubIntake(createIntakeDate(LocalDateTime.of(2025, Month.MARCH, 4, 12, 30)), firstTreatment)


    private val secondTreatmentFirstIntake =
        createStubIntake(createIntakeDate(LocalDateTime.of(2025, Month.MARCH, 2, 12, 30)), secondTreatment)
    private val secondTreatmentSecondIntake =
        createStubIntake(createIntakeDate(LocalDateTime.of(2025, Month.MARCH, 2, 12, 30)), secondTreatment)
    private val secondTreatmentThirdIntake =
        createStubIntake(createIntakeDate(LocalDateTime.of(2025, Month.MARCH, 2, 12, 30)), secondTreatment)


    private val thirdTreatmentFirstIntake =
        createStubIntake(createIntakeDate(LocalDateTime.of(2025, Month.MARCH, 4, 12, 30)), thirdTreatment)
    private val thirdTreatmentSecondIntake =
        createStubIntake(createIntakeDate(LocalDateTime.of(2025, Month.MARCH, 4, 12, 30)), thirdTreatment)
    private val thirdTreatmentThirdIntake =
        createStubIntake(createIntakeDate(LocalDateTime.of(2025, Month.MARCH, 4, 12, 30)), thirdTreatment)


    private val patients = listOf(firstPatient, secondPatient, thirdPatient)

    override fun get(): List<Patient> {
        firstTreatment.intakes.addAll(listOf(firstTreatmentFirstIntake, firstTreatmentSecondIntake, firstTreatmentThirdIntake))
        secondTreatment.intakes.addAll(listOf(secondTreatmentFirstIntake, secondTreatmentSecondIntake, secondTreatmentThirdIntake))
        thirdTreatment.intakes.addAll(listOf(thirdTreatmentFirstIntake, thirdTreatmentSecondIntake, thirdTreatmentThirdIntake))

        firstTreatment.patient = firstPatient
        secondTreatment.patient = secondPatient
        thirdTreatment.patient = thirdPatient
        return patients
    }

    private fun createStubPatient(id: Long, patientAddress: PatientAddress, treatments: MutableList<Treatment>) = Patient(
        id, "Name", "Lastname", LocalDate.of(1999, Month.DECEMBER, 20), mutableListOf(),
        Gender.XX, mutableListOf(), treatments, patientAddress
    )

    private fun createStubPatientAddress(id: Long = 1L, voivodeship: String = "Voivodeship") = PatientAddress(
        id, "Country", voivodeship, "District", "Community", "Location",
        "Street", "10A", "5B", "40200"
    )

    private fun createStubTreatment(id: Long = 1L, intakes: MutableList<Intake>) = Treatment(
        id,
        "Disease",
        "Description",
        "Code",
        mutableListOf(),
        null,
        intakes,
        LocalDateTime.of(2025, Month.FEBRUARY, 2, 12, 30),
        LocalDateTime.of(2025, Month.MAY, 4, 12, 50)
    )

    private fun createStubIntake(intakeDate: IntakeDate, treatment: Treatment) = Intake(
        1L,
        3L,
        IntakeForm.SHOT,
        3,
        IntakeFrequency.ONCE_A_DAY,
        mutableListOf(intakeDate),
        4,
        treatment
    )

    private fun createIntakeDate(intakeDate: LocalDateTime) = IntakeDate(
        1L,
        date = intakeDate,
        expectedDateMaxGap = intakeDate.plusDays(25),
        expectedDateMinGap = intakeDate.plusHours(23),
        intakeInTimeGap = true,
        overdose = false,
        intake = null
    )
}
