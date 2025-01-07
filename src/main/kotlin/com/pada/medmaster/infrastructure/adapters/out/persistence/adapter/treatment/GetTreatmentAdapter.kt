package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.GetActiveTreatmentsByCodePort
import com.pada.medmaster.application.ports.out.GetTreatmentPort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class GetTreatmentAdapter(val treatmentRepository: TreatmentRepository) : GetTreatmentPort {
    override fun get(id: Long): Treatment {
        return treatmentRepository.findById(id).asDomain()
    }

}