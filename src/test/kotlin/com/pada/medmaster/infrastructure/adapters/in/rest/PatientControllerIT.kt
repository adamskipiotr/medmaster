package com.pada.medmaster.infrastructure.adapters.`in`.rest

import MedMasterApplicationTests
import com.pada.medmaster.application.dto.request.patient.CreatePatientAddressRequest
import com.pada.medmaster.application.dto.request.patient.CreatePatientRequest
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
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
import java.time.Month

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PatientControllerIT : MedMasterApplicationTests() {

    @Autowired
    protected lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var patientRepository: PatientRepository

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
}