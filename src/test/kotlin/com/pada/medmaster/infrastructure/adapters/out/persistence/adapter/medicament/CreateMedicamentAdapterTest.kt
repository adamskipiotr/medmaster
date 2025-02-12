package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament

import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CreateMedicamentAdapterTest {

    @Autowired
    private lateinit var medicamentRepository: MedicamentRepository
    private val createMedicamentAdapter = CreateMedicamentAdapter(medicamentRepository)

    @Test
    fun should_do_when(){

    }
}