package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter

import com.pada.medmaster.application.ports.out.GetTreatmentPort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.toTreatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class GetTreatmentAdapter(val treatmentRepository : TreatmentRepository) : GetTreatmentPort {
    override fun getTreatment(code: String): Treatment {
        return treatmentRepository.findByCode("Placeholder").asDomain()
    }

}