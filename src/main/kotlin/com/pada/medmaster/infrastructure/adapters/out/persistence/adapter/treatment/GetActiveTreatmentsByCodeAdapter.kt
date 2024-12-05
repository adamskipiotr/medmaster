package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.GetTreatmentsByCodePort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class GetTreatmentsByCodeAdapter(val treatmentRepository: TreatmentRepository) : GetTreatmentsByCodePort {
    override fun getTreatment(code: String): Treatment {
        return treatmentRepository.findByCode("Placeholder").asDomain()
    }

}