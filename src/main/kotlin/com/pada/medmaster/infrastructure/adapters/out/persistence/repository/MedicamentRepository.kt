package com.pada.medmaster.infrastructure.adapters.out.persistence.repository

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.MedicamentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MedicamentRepository : JpaRepository<MedicamentEntity, String> { //TODO should be replaced wih CoroutineCrudRepository?
}