package com.pada.medmaster.infrastructure.adapters.out.persistence.repository

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.TreatmentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TreatmentRepository : JpaRepository<TreatmentEntity, String> { //TODO should be replaced wih CoroutineCrudRepository?

    fun findByCode(code : String) : TreatmentEntity
}