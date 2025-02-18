package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.MedMasterApplication
import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.domain.model.patient.PatientAddress
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.PatientEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate
import java.time.Month

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MedMasterApplication::class])
class UpdatePatientAdapterTest {

    @Mock
    private lateinit var patientRepository: PatientRepository
   @InjectMocks
    private lateinit var updatePatientAdapter: UpdatePatientAdapter


    @Test
    fun shouldUpdatePatientWhenPatientEntityForPatientIdInDb() {
        whenever(patientRepository.findById(100L)).thenReturn(PatientEntity())
        val patient = createNewPatientDataWithAddress()

        val result = updatePatientAdapter.update(patient)
    }

    @Test
    fun shouldThrowExceptionWhenNoPatientEntityForPatientIdInDb() {
        val patient = createNewPatientDataWithAddress(999L)

        val exception = assertThrows<RuntimeException> {
            updatePatientAdapter.update(patient)
        }
    }

    private fun createNewPatientDataWithAddress(id: Long = 100L): Patient {
        val address = PatientAddress(
            id,
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