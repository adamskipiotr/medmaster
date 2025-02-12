package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

class GetPatientAdapterTest {

    @Autowired
    private lateinit var patientRepository: PatientRepository
    private val getPatientAdapter = GetPatientAdapter(patientRepository)

    @Test
    fun should_returnPatient_whenIfHasItEntityInDb(){

        val result = getPatientAdapter.get(100L)
    }

    @Test
    fun should_throwException_whenIfHasNoEntityInDb(){

        //when
        val exception = assertThrows<RuntimeException> {
            getPatientAdapter.get(999L)
        }

        //then
        assertEquals("No pharmacy with medicament Medicament 1 found in voivodeship: OtherVoivodeship", exception.message)

    }
}