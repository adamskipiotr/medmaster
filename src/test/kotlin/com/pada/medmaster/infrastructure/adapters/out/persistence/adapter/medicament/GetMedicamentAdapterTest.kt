package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament

import com.pada.medmaster.domain.exception.IngredientProhibitedInPatientCountryException
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

class GetMedicamentAdapterTest {

    @Autowired
    private lateinit var medicamentRepository: MedicamentRepository
    private val getMedicamentAdapter = GetMedicamentAdapter(medicamentRepository)

    @Test
    fun should_returnMedicament_whenIfHasItEntityInDb(){

        val result = getMedicamentAdapter.get(100L)
    }

    @Test
    fun should_throwException_whenIfHasNoEntityInDb(){

        //when
        val exception = assertThrows<RuntimeException> {
            getMedicamentAdapter.get(999L)
        }

        //then
        assertEquals("No pharmacy with medicament Medicament 1 found in voivodeship: OtherVoivodeship", exception.message)
    }
}