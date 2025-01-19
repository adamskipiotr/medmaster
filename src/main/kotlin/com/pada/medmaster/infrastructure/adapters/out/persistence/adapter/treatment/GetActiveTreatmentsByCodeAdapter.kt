package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.treatment.GetActiveTreatmentsByCodePort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class GetActiveTreatmentsByCodeAdapter(val treatmentRepository: TreatmentRepository) : GetActiveTreatmentsByCodePort {
    override fun get(code: String): List<Treatment> {
        return treatmentRepository.findByCode(code).map{ it.asDomain()}
    }

}