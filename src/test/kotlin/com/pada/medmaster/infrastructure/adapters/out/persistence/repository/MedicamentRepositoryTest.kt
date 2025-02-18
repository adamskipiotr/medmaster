package com.pada.medmaster.infrastructure.adapters.out.persistence.repository

import MedMasterApplicationTests
import com.pada.medmaster.MedMasterApplication
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MedMasterApplication::class])
@Sql(scripts = ["/medicaments.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
class MedicamentRepositoryTest : MedMasterApplicationTests() {

    @Autowired
    private lateinit var medicamentsRepository: MedicamentRepository

    @Test
    fun shouldGetAllMedicaments() {

        val result = medicamentsRepository.findAll()

        assertEquals(5, result.size)
    }

    @Test
    fun shouldGetMedicamentById() {

        val result = medicamentsRepository.findById(100L)

        assertTrue(result.isPresent)
        assertEquals("Medicament100", result.get().name)
    }

    @Test
    fun shouldReturnEmptyOptionalWhenIfHasNoEntityInDb() {

        val result = medicamentsRepository.findById(999L)

        assertTrue(result.isEmpty)
    }
}