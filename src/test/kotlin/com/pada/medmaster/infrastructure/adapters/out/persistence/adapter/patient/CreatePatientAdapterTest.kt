package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CreatePatientAdapterTest {

    @Autowired
    private lateinit var patientRepository: PatientRepository
    private val createPatientAdapter = CreatePatientAdapter(patientRepository)

    @Test
    fun should_do_when(){

    }
}