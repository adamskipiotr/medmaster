package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament

import com.pada.medmaster.MedMasterApplication
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MedMasterApplication::class])
class CreateMedicamentAdapterTest {

    @Mock
    private lateinit var medicamentRepository: MedicamentRepository
   @InjectMocks
    private lateinit var createMedicamentAdapter: CreateMedicamentAdapter

    @BeforeEach
    fun setUp() {
        createMedicamentAdapter = CreateMedicamentAdapter(medicamentRepository)
    }

    @Test
    fun should_do_when() {

    }
}