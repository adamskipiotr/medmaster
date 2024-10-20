package com.pada.medmaster.infrastructure.adapters.out.persistence.repository

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.TreatmentEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TreatmentRepository : CoroutineCrudRepository<TreatmentEntity, String> {

    fun findByCode(code : String) : TreatmentEntity
}