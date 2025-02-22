package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.PatientEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.whenever
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate

@ExtendWith(SpringExtension::class)
class GetPatientAdapterTest {

    @Mock
    private lateinit var patientRepository: PatientRepository

    @InjectMocks
    private lateinit var getPatientAdapter: GetPatientAdapter


    @Test
    fun shouldGetPatientMappedToDomain() {
        val patientEntity = createPatientEntity()
        whenever(patientRepository.findById(100L)).thenReturn(patientEntity) // no when in Kotlin

        //when
        val result = getPatientAdapter.get(100)

        //then
        assertEquals("Patient100", result.name)   // refactor assertions
    }


    private fun createPatientEntity(): PatientEntity {
        return PatientEntity().apply {
            id = 100L
            name = "Patient100"
            lastName = "Doe"
            birthDate = LocalDate.of(1990, 5, 15)
            gender = Gender.XY
            specialHealthConditions = mutableListOf()
            treatments = mutableListOf()
            address = null
            allergicIngredients = mutableListOf()
        }
    }
}