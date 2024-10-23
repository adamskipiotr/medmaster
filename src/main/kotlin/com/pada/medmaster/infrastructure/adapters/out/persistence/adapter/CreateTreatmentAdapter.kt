package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter

import com.pada.medmaster.application.ports.out.CreateTreatmentPort
import com.pada.medmaster.application.ports.out.GetTreatmentPort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.TreatmentEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class CreateTreatmentAdapter(val treatmentRepository : TreatmentRepository) : CreateTreatmentPort {

    override fun createTreatment(treatment: Treatment) {
       treatmentRepository.save(TreatmentEntity.of(treatment))
    }
}