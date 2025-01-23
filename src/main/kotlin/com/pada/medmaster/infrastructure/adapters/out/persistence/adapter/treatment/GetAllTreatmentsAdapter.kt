package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.treatment.GetAllTreatmentsPort
import com.pada.medmaster.domain.model.patient.Treatment
import org.springframework.stereotype.Component

@Component
class GetAllTreatmentsAdapter() : GetAllTreatmentsPort {
    override fun getAllTreatments(): List<Treatment> {
        return emptyList() // treatmentRepository.findAll().map { it.asDomain() }
    }

}