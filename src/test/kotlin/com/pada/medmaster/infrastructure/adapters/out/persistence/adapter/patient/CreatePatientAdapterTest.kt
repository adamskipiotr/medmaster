package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.MedMasterApplication
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MedMasterApplication::class])
class CreatePatientAdapterTest {

    @Mock
    private lateinit var patientRepository: PatientRepository
    @InjectMocks
    private lateinit var createPatientAdapter: CreatePatientAdapter


    @Test
    fun should_do_when() {

    }
}