package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.ports.out.patient.GetPatientPort
import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import java.time.LocalDate
import java.time.Month

class GetFakePatientPort() : GetPatientPort {

    private val patients = mutableMapOf(
        1L to Patient(1L,"Name", "Lastname", LocalDate.of(1999, Month.DECEMBER,20), mutableListOf(),
            Gender.XX, mutableListOf(), mutableListOf(), null))

    override fun get(id: Long): Patient {
        return patients[id] ?: throw NoSuchElementException("Patient not found")
    }
}
