package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament

import com.pada.medmaster.MedMasterApplication
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.MedicamentEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MedMasterApplication::class])
class GetMedicamentAdapterTest {

    @Mock
    private lateinit var medicamentRepository: MedicamentRepository
   @InjectMocks
    private lateinit var getMedicamentAdapter: GetMedicamentAdapter

    @Test
    fun shouldGetMedicamentMappedToDomain() {
        val medicamentEntity = createMedicamentEntity()
        whenever(medicamentRepository.findById(100L)).thenReturn(Optional.of(medicamentEntity)) // no when in Kotlin

        //when
        val result = getMedicamentAdapter.get(100)

        //then
        assertEquals("Medicament100", result.name)   // refactor assertions
    }


    private fun createMedicamentEntity(): MedicamentEntity {
        return MedicamentEntity().apply {
            id = 100L
            name = "Medicament100"
            overdoseCounteractions = "OverdoseCounteractions100"
            ingredientsIds = mutableListOf(100)
        }
    }
}