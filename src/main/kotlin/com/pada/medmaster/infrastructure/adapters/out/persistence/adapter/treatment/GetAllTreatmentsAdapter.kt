package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.treatment.GetAllTreatmentsPort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class GetAllTreatmentsAdapter(val treatmentRepository: TreatmentRepository) : GetAllTreatmentsPort {
    override fun getAllTreatments(): List<Treatment> {
        return treatmentRepository.findAll().map { it.asDomain() }
    }

}