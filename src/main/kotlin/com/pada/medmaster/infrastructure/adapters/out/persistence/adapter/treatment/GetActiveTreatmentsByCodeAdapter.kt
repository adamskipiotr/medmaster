package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.treatment.GetActiveTreatmentsByCodePort
import com.pada.medmaster.domain.model.patient.Treatment
import org.springframework.stereotype.Component

@Component
class GetActiveTreatmentsByCodeAdapter() : GetActiveTreatmentsByCodePort {
    override fun get(code: String): List<Treatment> {
        return emptyList()// treatmentRepository.findByCode(code).map{ it.asDomain()}
    }

}