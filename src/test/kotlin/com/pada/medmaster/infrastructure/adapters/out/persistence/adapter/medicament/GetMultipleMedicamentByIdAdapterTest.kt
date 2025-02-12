package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament

import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient.GetPatientAdapter
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class GetMultipleMedicamentByIdAdapterTest {

    @Autowired
    private lateinit var medicamentRepository: MedicamentRepository
    private val getMultipleMedicamentByIdAdapter = GetMultipleMedicamentByIdAdapter(medicamentRepository)

    @Test
    fun should_returnSingleMedicament_whenOnlyOneIdHasEntityInDb() {

        val result = getMultipleMedicamentByIdAdapter.get(listOf(100L))
    }

    @Test
    fun should_getMultipleMedicaments_whenAllIdsHaveEntitiesInDb() {

        val result = getMultipleMedicamentByIdAdapter.get(listOf(100L, 101L, 102L))
    }

    @Test
    fun should_getEmptyList_whenNoEntityFoundForGivenIdsInDb() {

        val result = getMultipleMedicamentByIdAdapter.get(listOf(999L, 998L, 997L))
    }
}