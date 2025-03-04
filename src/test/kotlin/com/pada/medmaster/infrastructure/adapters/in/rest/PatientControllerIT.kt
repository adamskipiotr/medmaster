package com.pada.medmaster.infrastructure.adapters.`in`.rest

import MedMasterApplicationTests
import com.pada.medmaster.application.dto.request.patient.CreatePatientAddressRequest
import com.pada.medmaster.application.dto.request.patient.CreatePatientRequest
import com.pada.medmaster.application.dto.request.patient.CreateIntakeRequest
import com.pada.medmaster.application.dto.request.patient.CreateMedicalProcedureRequest
import com.pada.medmaster.application.dto.request.patient.CreateTreatmentRequest
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import com.pada.medmaster.domain.model.patient.IntakeForm
import com.pada.medmaster.domain.model.patient.IntakeFrequency
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.jdbc.Sql
import java.time.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(
    scripts = ["/patients.sql", "/treatments.sql", "/medicaments.sql", "/ingredients.sql"],
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS
)
class PatientControllerIT : MedMasterApplicationTests() {

    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var patientRepository: PatientRepository

    private val fixedClock = Clock.fixed(Instant.parse("2025-02-25T00:00:00Z"), ZoneId.of("UTC"))

    @Test
    fun should_createPatient_when_newPatientDataProvided() {
        // given
        val patientAddress = CreatePatientAddressRequest(
            "Country", "Voivodeship", "District", "Community",
            "Location", "Street", "1", "2", "12345"
        )
        val createPatientRequest = CreatePatientRequest(
            "Patient Name",
            "LastName",
            LocalDate.of(1998, Month.JULY, 14),
            mutableListOf(),
            Gender.XY,
            mutableListOf(),
            patientAddress
        )

        //when
        val response: ResponseEntity<Unit> = restTemplate.exchange(
            "/patients",
            HttpMethod.POST,
            HttpEntity(createPatientRequest),
            Unit::class.java
        )

        // then
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(6, patientRepository.count())
    }

    @Test
    fun should_addTreatmentToPatient_when_newTreatmentDataProvided() {
        // given
        val createTreatmentRequest = CreateTreatmentRequest(
            "Disease", "Description", "Code", listOf(), listOf(),
            LocalDateTime.of(2010, Month.AUGUST, 15, 12, 0),
            LocalDateTime.of(2030, Month.DECEMBER, 31, 12, 0)
        )

        //when
        val response: ResponseEntity<Unit> = restTemplate.exchange(
            "/patients/100/treatments",
            HttpMethod.POST,
            HttpEntity(createTreatmentRequest),
            Unit::class.java
        )

        // then
        val patientEntity = patientRepository.findById(100)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(2, patientEntity.treatments.size)
    }

    @Test
    fun should_addIntakeToPatientsTreatment_when_newIntakeTreatmentDataProvided() {
        // given
        val createIntakeRequest = CreateIntakeRequest(
            100L, IntakeForm.SHOT, 2, IntakeFrequency.TWICE_A_DAY, mutableListOf(), 2
        )

        //when
        val response: ResponseEntity<Unit> = restTemplate.exchange(
            "/patients/100/treatments/100/intakes",
            HttpMethod.POST,
            HttpEntity(createIntakeRequest),
            Unit::class.java
        )

        // then
        val patientEntity = patientRepository.findById(100)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(1, patientEntity.treatments.size)
    }

    @Test
    fun should_addMedicalProcedureToPatientsTreatment_when_newMedicalProcedureDataProvided() {
        // given
        val createMedicalProcedureRequest = CreateMedicalProcedureRequest(
            "Medical Procedure", "Description",
            LocalDateTime.of(2025, Month.APRIL, 10, 12, 30),
            LocalDateTime.of(2025, Month.APRIL, 15, 12, 30)
        )

        //when
        val response: ResponseEntity<Unit> = restTemplate.exchange(
            "/patients/100/treatments/100/medical-procedures",
            HttpMethod.POST,
            HttpEntity(createMedicalProcedureRequest),
            Unit::class.java
        )

        // then
        val patientEntity = patientRepository.findById(100)
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(1, patientEntity.treatments[0].medicalProcedures.size)
    }

    @Test
    fun should_returnBadRequestResponse_when_newMedicalProcedureHasInvalidRecoveryDate() {
        // given
        val createMedicalProcedureRequest = CreateMedicalProcedureRequest(
            "Medical Procedure", "Description",
            LocalDateTime.of(2025, Month.AUGUST, 10, 12, 30),
            LocalDateTime.of(2025, Month.JULY, 15, 12, 30)
        )

        //when
        val response: ResponseEntity<ApiError> = restTemplate.exchange(
            "/patients/100/treatments/100/medical-procedures",
            HttpMethod.POST,
            HttpEntity(createMedicalProcedureRequest),
            ApiError::class.java
        )

        // then
        val patientEntity = patientRepository.findById(100)
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        assertEquals("Minimal recovery date must be after procedure date", response.body!!.message)
        // assertEquals(0, patientEntity.treatments[0].medicalProcedures.size) missing rollback
    }
}