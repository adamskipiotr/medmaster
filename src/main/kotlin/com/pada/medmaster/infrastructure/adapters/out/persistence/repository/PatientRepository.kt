package com.pada.medmaster.infrastructure.adapters.out.persistence.repository

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.PatientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository :
    JpaRepository<PatientEntity, String> { //TODO should be replaced wih CoroutineCrudRepository?

    fun findById(id: Long): PatientEntity
}
