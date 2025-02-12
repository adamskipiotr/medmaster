package com.pada.medmaster.infrastructure.adapters.`in`.rest

import MedMasterApplicationTests
import com.pada.medmaster.application.dto.request.patient.CreatePatientAddressRequest
import com.pada.medmaster.application.dto.request.patient.CreatePatientRequest
import com.pada.medmaster.application.dto.request.treatment.CreateIntakeRequest
import com.pada.medmaster.application.dto.request.treatment.CreateTreatmentRequest
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.MedicamentEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.*
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatientControllerIT : MedMasterApplicationTests() {

    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var patientRepository: PatientRepository

    @Autowired
    private lateinit var medicamentRepository: MedicamentRepository

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
        assertEquals(1, patientRepository.count())
    }

    @Test
    fun should_addTreatmentToPatient_when_newTreatmentDataProvided() {
        // given
        val patientEntity = PatientEntity()
        patientEntity.id = 100
        patientRepository.save(patientEntity)

        val createTreatmentRequest = CreateTreatmentRequest(
            "Disease", "Description", "Code", listOf(), listOf(),
            LocalDateTime.of(2010, Month.AUGUST, 15, 12, 0),
            LocalDateTime.of(2030, Month.DECEMBER, 31, 12, 0)
        )

        //when
        val response: ResponseEntity<Unit> = restTemplate.exchange(
            "/100/treatments",
            HttpMethod.POST,
            HttpEntity(createTreatmentRequest),
            Unit::class.java
        )

        // then
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(1, patientEntity.treatments.size)
    }

    @Test
    fun should_addIntakeToPatientsTreatment_when_newIntakeTreatmentDataProvided() {
        // given
        val treatmentEntity = TreatmentEntity()
        treatmentEntity.id = 101
        val patientEntity = PatientEntity()
        patientEntity.id = 100
        patientEntity.addTreatment(treatmentEntity)
        patientRepository.save(patientEntity)

        val medicamentEntity = MedicamentEntity()
        medicamentEntity.id = 50
        medicamentRepository.save(medicamentEntity)

        val createIntakeRequest = CreateIntakeRequest(
            50L, IntakeForm.SHOT, 2, IntakeFrequency.TWICE_A_DAY, mutableListOf(), 2
        )

        //when
        val response: ResponseEntity<Unit> = restTemplate.exchange(
            "/100/treatments/101/intakes",
            HttpMethod.POST,
            HttpEntity(createIntakeRequest),
            Unit::class.java
        )

        // then
        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(1, patientEntity.treatments.size)
    }
}