package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.domain.model.patient.PatientAddress
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate
import java.time.Month

class UpdatePatientAdapterTest {

    @Autowired
    private lateinit var patientRepository: PatientRepository
    private val updatePatientAdapter = UpdatePatientAdapter(patientRepository)

    @Test
    fun should_updatePatient_whenPatientEntityForPatientIdInDb() {
        val patient = createNewPatientDataWithAddress()

        val result = updatePatientAdapter.update(patient)
    }

    @Test
    fun should_throwException_whenNoPatientEntityForPatientIdInDb() {
        val patient = createNewPatientDataWithAddress(999L)

        val exception = assertThrows<RuntimeException> {
            updatePatientAdapter.update(patient)
        }
    }

    private fun createNewPatientDataWithAddress(id: Long = 100L): Patient{
        val address = PatientAddress(
            1L,
            "New Country",
            "New Voivodeship",
            "New District",
            "New Community",
            "New Loction",
            "New Street",
            "105",
            "205",
            "54321"
        )
        return Patient(
            id, "New Name", "Lastname", LocalDate.of(2024, Month.FEBRUARY, 14),
            mutableListOf(), Gender.XX, mutableListOf(), mutableListOf(), address
        )
    }


}