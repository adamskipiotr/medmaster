package com.pada.medmaster.infrastructure.adapters.out.persistence.repository

import MedMasterApplicationTests
import com.pada.medmaster.MedMasterApplication
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MedMasterApplication::class])
@Sql(scripts = ["/patients.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
class PatientRepositoryTest : MedMasterApplicationTests() {

    @Autowired
    private lateinit var patientRepository: PatientRepository

    @Test
    fun shouldGetAllPatients() {

        val result = patientRepository.findAll()

        assertEquals(5, result.size)
    }

    @Test
    fun shouldGetPatientById() {

        val result = patientRepository.findById(100L)

        assertEquals("Patient100", result.name)
    }

    @Test
    fun should_throwException_whenIfHasNoEntityInDb() {

        //when
        val exception = assertThrows<RuntimeException> {
            patientRepository.findById(999L)
        }

        //then
        assertEquals(
            "Result must not be null",
            exception.message
        )
    }
}