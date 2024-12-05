package com.pada.medmaster.infrastructure.adapters.out.persistence.repository

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.TreatmentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TreatmentRepository :
    JpaRepository<TreatmentEntity, String> { //TODO should be replaced wih CoroutineCrudRepository?

    @Query("SELECT t FROM TreatmentEntity t WHERE t.code = :code AND t.endDate IS NULL")
    fun findByCode(code: String): List<TreatmentEntity>
}