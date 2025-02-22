package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament

import com.pada.medmaster.MedMasterApplication
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MedMasterApplication::class])
class GetMultipleMedicamentByIdAdapterTest {

    @Mock
    private lateinit var medicamentRepository: MedicamentRepository
    @InjectMocks
    private lateinit var getMultipleMedicamentByIdAdapter: GetMultipleMedicamentByIdAdapter


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